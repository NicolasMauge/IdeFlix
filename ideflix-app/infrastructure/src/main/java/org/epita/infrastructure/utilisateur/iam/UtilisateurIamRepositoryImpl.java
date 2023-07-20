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

        ResponseEntity<String> creationResponse = restTemplate.postForEntity(iamCreationEndpointUrl,
                utilisateurIamApiMapper.mapEntityToCreationApiDto(nouvelUtilisateurIam),
                String.class);

        HttpStatus codeReponseHttp = creationResponse.getStatusCode();

        logger.debug("APP - Création " + nouvelUtilisateurIam.getEmail() + ". Réponse de l'IAM : " + codeReponseHttp.name());

        String creationReponseBody = creationResponse.getBody();

        switch (codeReponseHttp) {
            case CREATED:
                UtilisateurIamCreationReponseApiDto utilisateurIamCreationReponseApiDto = null;
                try {
                    utilisateurIamCreationReponseApiDto = objectMapper.readValue(
                            creationReponseBody,
                            UtilisateurIamCreationReponseApiDto.class);
                } catch (JsonProcessingException e) {
                    logger.error("APP - IAM - Erreur lecture JSON : " + creationReponseBody);
                    throw new IamJsonConversionException("APP - IAM - Le compte "
                            + nouvelUtilisateurIam.getEmail()
                            + "a été créé dans l'IAM mais la réponse n'a pas pu être interprêtée : "
                            + creationReponseBody);
                }

                return utilisateurIamApiMapper.mapCreationReponseDtoToEntity(utilisateurIamCreationReponseApiDto);


            case CONFLICT:
                ErrorIamApiDto errorIamApiDto = null;

                try {
                    errorIamApiDto = objectMapper.readValue(
                            creationReponseBody,
                            ErrorIamApiDto.class);

                } catch (JsonProcessingException e) {
                    logger.error("APP - IAM - Erreur lecture JSON : " + creationReponseBody);
                    throw new IamJsonConversionException("APP - IAM - Le compte "
                            + nouvelUtilisateurIam.getEmail()
                            + "existe déjà dans l'IAM. De plus, la réponse n'a pas pu être interprêtée : "
                            + creationReponseBody);
                }

                logger.warn("APP - IAM : l'utilisateur " + nouvelUtilisateurIam.getEmail() + " existe déjà.");
                throw new IamUtilisateurExisteDejaException("(" + nouvelUtilisateurIam.getEmail() + ").");

            default:
                throw new IamException("Code retour : " + codeReponseHttp.name() + " (" + nouvelUtilisateurIam.getEmail() + ").");
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
