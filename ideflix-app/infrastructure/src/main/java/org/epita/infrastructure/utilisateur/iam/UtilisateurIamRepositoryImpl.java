package org.epita.infrastructure.utilisateur.iam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epita.domaine.common.IamJsonException;
import org.epita.domaine.common.IamUtilisateurInterditException;
import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.infrastructure.utilisateur.iam.mapper.UtilisateurIamApiMapper;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamCreationReponseApiDto;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamLoginReponseApiDto;
import org.epita.domaine.common.IamException;
import org.epita.domaine.common.IamUtilisateurExisteDejaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import java.util.List;
import java.util.Objects;

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

                    throw new IamException("APP - IAM - Echec de la création de l'utilisateur. Code retour : " + e.getStatusCode() + " (" + nouvelUtilisateurIam.getEmail() + ").");
            }
        } catch (Exception e) {
            logger.error("APP - IAM - Création d'utilisateur : exception non prévue.");
            throw new IamException("APP - IAM - Echec de la création de l'utilisateur " + nouvelUtilisateurIam.getEmail() + ".");
        }

    }

    @Override
    public UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIam) {
        final String iamLoginEndpointUrl = "http://localhost:8080/api/v1/iam/login";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<UtilisateurIamLoginReponseApiDto> loginResponse = restTemplate.postForEntity(
                    iamLoginEndpointUrl,
                    utilisateurIamApiMapper.mapEntiteToLoginApiDto(utilisateurIam),
                    UtilisateurIamLoginReponseApiDto.class);

            return utilisateurIamApiMapper.mapLoginReponseApiDtoToEntity(Objects.requireNonNull(loginResponse.getBody()));

        } catch (HttpClientErrorException e) {
            switch (e.getStatusCode()) {
                case FORBIDDEN:
                    logger.warn("IdeFlix - Login de " + utilisateurIam.getEmail()
                            + " échoué.");

                    throw new IamUtilisateurInterditException("APP - IAM - Erreur login de " + utilisateurIam.getEmail()
                            + " : email ou mot de passe erroné.");

                default:
                    logger.error("IdeFlix - Erreur login de  " + utilisateurIam.getEmail()
                            + " n'a pas été créé. L'IAM a répondu : " + e.getStatusCode()
                            + " - " + e.getResponseBodyAsString());

                    throw new IamException("IdeFlix - Erreur login - Code retour : " + e.getStatusCode() + " (" + utilisateurIam.getEmail() + ").");
            }
        } catch (UnknownContentTypeException e) {
            ObjectMapper objectMapper = new ObjectMapper();

            logger.warn("IdeFlix - Echec de la conversion JSON par postForEntity. Utilisation d'un ObjectMapper.");

            String reponseStr = new String(e.getResponseBody());

            try {
                UtilisateurIamLoginReponseApiDto utilisateurIamLoginReponseApiDto = objectMapper.readValue(reponseStr, UtilisateurIamLoginReponseApiDto.class);
                switch (e.getRawStatusCode()) {
                    case 200:
                        logger.warn("IdeFlix - Création de l'utilisateur" + utilisateurIam.getEmail() + ". Code : " + e.getRawStatusCode() + ".");
                        return utilisateurIamApiMapper.mapLoginReponseApiDtoToEntity(utilisateurIamLoginReponseApiDto);

                    default:
                        logger.error("IdeFlix - Echec de la conversion JSON par postForEntity puis de la conversion par l'ObjectMapper. L'utilisateur " + utilisateurIam.getEmail() + " n'a pas été créé dans l'IAM. Code : " + e.getRawStatusCode() + ".");
                        throw new IamException("IdeFlix - Echec de la création de l'utilisateur " + utilisateurIam.getEmail() + ". Code : " + e.getRawStatusCode() + ".");

                }

            } catch (Exception jsonException) {
                logger.error("IdeFlix - Echec de conversion JSON. Echec de la création de l'utilisateur dans l'IAM " + utilisateurIam.getEmail() + ". Code : " + e.getRawStatusCode() + ".");
                throw new IamJsonException("IdeFlix - Echec de conversion JSON. Echec de la création de l'utilisateur dans l'IAM " + utilisateurIam.getEmail() + ". Code : " + e.getRawStatusCode() + ".");
            }
        } catch (Exception ex) {

            logger.error("IdeFlix - loginIam exception non prévue  : "
                    + ex.getMessage()
                    + (ex.getCause() == null ? " - " : " - " + ex.getCause()) + ex.getCause());
            throw new IamException("IdeFlix - Echec de la création de l'utilisateur " + utilisateurIam.getEmail() + ".");
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
