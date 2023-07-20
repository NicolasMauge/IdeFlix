package org.epita.infrastructure.utilisateur.iam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.epita.domaine.common.IamJsonConversionException;
import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.infrastructure.utilisateur.iam.apidto.ErrorIamApiDto;
import org.epita.infrastructure.utilisateur.iam.mapper.UtilisateurIamApiMapper;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamCreationReponseApiDto;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamLoginReponseApiDto;
import org.epita.domaine.common.IamException;
import org.epita.domaine.common.IamUtilisateurExisteDejaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class UtilisateurIamRepositoryImpl implements UtilisateurIamRepository {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurIamRepositoryImpl.class);
    UtilisateurIamApiMapper utilisateurIamApiMapper;

    public UtilisateurIamRepositoryImpl(UtilisateurIamApiMapper utilisateurIamApiMapper) {
        this.utilisateurIamApiMapper = utilisateurIamApiMapper;
    }

    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity nouvelUtilisateurIam) {

        final String iamCreationEndpointUrl = "http://localhost:8080/api/v1/iam/utilisateur";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ResponseEntity<UtilisateurIamCreationReponseApiDto> creationResponse = restTemplate.postForEntity(iamCreationEndpointUrl,
                    utilisateurIamApiMapper.mapEntityToCreationApiDto(nouvelUtilisateurIam),
                    UtilisateurIamCreationReponseApiDto.class);

            UtilisateurIamCreationReponseApiDto utilisateurIamCreationReponseApiDto = creationResponse.getBody();

            if (utilisateurIamCreationReponseApiDto != null)
                return utilisateurIamApiMapper.mapCreationReponseDtoToEntity(utilisateurIamCreationReponseApiDto);
            else
                throw new IamException("APP - IAM - Echec création " + nouvelUtilisateurIam.getEmail() + ").");
        } catch (HttpClientErrorException e) {
            switch (e.getStatusCode()) {
                case CONFLICT:
                    logger.warn("APP - IAM - Le compte " + nouvelUtilisateurIam.getEmail()
                            + " existe déjà dans l'IAM.");

                    throw new IamUtilisateurExisteDejaException("APP - IAM - Le compte " + nouvelUtilisateurIam.getEmail()
                            + " existe déjà dans l'IAM.");

                default:
                    logger.error("APP - IAM - Le compte " + nouvelUtilisateurIam.getEmail()
                            + " n'a pas été créé. L'IAM a répondu : " + e.getStatusCode()
                            + " - " + e.getResponseBodyAsString());

                    throw new IamException("APP - IAM - Code retour : " + e.getStatusCode() + " (" + nouvelUtilisateurIam.getEmail() + ").");
            }
        } catch (Exception e) {
            logger.debug("APP - IAM postForEntity exception non prévue.");
            throw new IamException("APP - IAM - Echec de la création de l'utilisateur " + nouvelUtilisateurIam.getEmail() + ".");
        }

    }

    @Override
    public UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIam) {

        final String iamLoginEndpointUrl = "http://localhost:8080/api/v1/iam/login";
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> loginResponse = restTemplate.postForEntity(
                iamLoginEndpointUrl,
                utilisateurIamApiMapper.mapEntiteToLoginApiDto(utilisateurIam),
                String.class);


        HttpStatus codeReponseHttp = loginResponse.getStatusCode();

        logger.debug("APP - Création " + utilisateurIam.getEmail() + ". Réponse de l'IAM : " + codeReponseHttp.name());

        switch (codeReponseHttp) {
            case OK:
                String reponseLoginString = loginResponse.getBody();
                logger.debug("APP - Création " + utilisateurIam.getEmail() + ". Réponse de l'IAM : " + reponseLoginString);

                try {
                    return utilisateurIamApiMapper.mapLoginReponseApiDtoToEntity(
                            objectMapper
                                    .readValue(reponseLoginString, UtilisateurIamLoginReponseApiDto.class));
                } catch (JsonProcessingException e) {
                    logger.error("APP - IAM : pas possible de convertir ce JSON en objet : " + reponseLoginString);
                    throw new RuntimeException(e);
                }
            default:
                throw new IamException("Code retour : " + codeReponseHttp.name() + " (" + utilisateurIam.getEmail() + ").");
        }
    }

    @Override
    public List<UtilisateurIamEntity> getUtilisateursIam() {
        return null;
    }

    @Override
    public void delUtilisateurIam(String email) {

    }
}
