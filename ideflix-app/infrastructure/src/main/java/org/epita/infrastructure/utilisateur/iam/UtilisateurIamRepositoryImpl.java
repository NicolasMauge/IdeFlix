package org.epita.infrastructure.utilisateur.iam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.epita.domaine.common.IamJsonException;
import org.epita.domaine.common.IamUtilisateurInterditException;
import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.infrastructure.utilisateur.iam.apidto.ListUtilisateurIamDetailApiDto;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamDetailApiDto;
import org.epita.infrastructure.utilisateur.iam.mapper.UtilisateurIamApiMapper;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamCreationReponseApiDto;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamLoginReponseApiDto;
import org.epita.domaine.common.IamException;
import org.epita.domaine.common.IamUtilisateurExisteDejaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import java.util.Arrays;
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
                throw new IamException("IdeFlix - IAM - Echec création " + nouvelUtilisateurIam.getEmail() + ").");
        } catch (HttpClientErrorException e) {
            switch (e.getStatusCode()) {
                case CONFLICT:
                    logger.warn("IdeFlix - IAM - Le compte " + nouvelUtilisateurIam.getEmail()
                            + " existe déjà dans l'IAM.");

                    throw new IamUtilisateurExisteDejaException("APP - IAM - Le compte " + nouvelUtilisateurIam.getEmail()
                            + " existe déjà dans l'IAM.");

                default:
                    logger.error("IdeFlix - IAM - Le compte " + nouvelUtilisateurIam.getEmail()
                            + " n'a pas été créé. L'IAM a répondu : " + e.getStatusCode()
                            + " - " + e.getResponseBodyAsString());

                    throw new IamException("IdeFlix - IAM - Echec de la création de l'utilisateur. Code retour : " + e.getStatusCode() + " (" + nouvelUtilisateurIam.getEmail() + ").");
            }
        } catch (Exception e) {
            logger.error("IdeFlix - IAM - Création d'utilisateur : exception non prévue.");
            throw new IamException("IdeFlix - IAM - Echec de la création de l'utilisateur " + nouvelUtilisateurIam.getEmail() + ".");
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
                    logger.warn("IdeFlix - loginIam - Login de " + utilisateurIam.getEmail()
                            + " échoué.");

                    throw new IamUtilisateurInterditException("IdeFlix - loginIam - Erreur login de " + utilisateurIam.getEmail()
                            + " : email ou mot de passe erroné.");

                default:
                    logger.error("IdeFlix - loginIam - Erreur login de  " + utilisateurIam.getEmail()
                            + " n'a pas pu se connecter. L'IAM a répondu : " + e.getStatusCode()
                            + " - " + e.getResponseBodyAsString());

                    throw new IamException("IdeFlix - Erreur login - Code retour : " + e.getStatusCode() + " (" + utilisateurIam.getEmail() + ").");
            }
        } catch (UnknownContentTypeException e) {
            ObjectMapper objectMapper = new ObjectMapper();

            logger.warn("IdeFlix - loginIam - Echec de la conversion JSON par postForEntity. Utilisation d'un ObjectMapper.");

            String reponseStr = new String(e.getResponseBody());

            try {
                UtilisateurIamLoginReponseApiDto utilisateurIamLoginReponseApiDto = objectMapper.readValue(reponseStr, UtilisateurIamLoginReponseApiDto.class);
                switch (e.getRawStatusCode()) {
                    case 200:
                        logger.warn("IdeFlix - loginIam - Connexion de l'utilisateur " + utilisateurIam.getEmail() + ". Code : " + e.getRawStatusCode() + ".");
                        return utilisateurIamApiMapper.mapLoginReponseApiDtoToEntity(utilisateurIamLoginReponseApiDto);

                    default:
                        logger.error("IdeFlix - loginIam - Echec de la conversion JSON par postForEntity puis de la conversion par l'ObjectMapper. L'utilisateur " + utilisateurIam.getEmail() + " n'a pas été créé dans l'IAM. Code : " + e.getRawStatusCode() + ".");
                        throw new IamException("IdeFlix - Echec de la connexion de l'utilisateur " + utilisateurIam.getEmail() + ". Code : " + e.getRawStatusCode() + ".");

                }

            } catch (Exception jsonException) {
                logger.error("IdeFlix - loginIam - Echec de conversion JSON. Echec de la connexion de l'utilisateur dans l'IAM " + utilisateurIam.getEmail() + ". Code : " + e.getRawStatusCode() + ".");
                throw new IamJsonException("IdeFlix - Echec de conversion JSON. Echec de la connexion de l'utilisateur dans l'IAM " + utilisateurIam.getEmail() + ". Code : " + e.getRawStatusCode() + ".");
            }
        } catch (Exception ex) {

            logger.error("IdeFlix - loginIam - Exception non prévue  : "
                    + ex.getMessage()
                    + (ex.getCause() == null ? " - " : " - " + ex.getCause()) + ex.getCause());
            throw new IamException("IdeFlix - Echec de la connexion de l'utilisateur " + utilisateurIam.getEmail() + ".");
        }
    }

    // ================================================================================================================
    @Override
    public List<UtilisateurIamEntity> getUtilisateursIam(String headerAuthorization) {

        final String iamGetUtilisateursEndpointUrl = "http://localhost:8080/api/v1/iam/admin/utilisateurs";
        RestTemplate restTemplate = new RestTemplate();

        logger.debug("IdeFlix - getUtilisateursIam - Appel GET à " + iamGetUtilisateursEndpointUrl);


        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", headerAuthorization);

            HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

            ResponseEntity<String> getUtilisateursResponse = restTemplate.exchange(iamGetUtilisateursEndpointUrl,
                    HttpMethod.GET,
                    requestEntity,
                    String.class);

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                List<UtilisateurIamDetailApiDto> listUtilisateurIamDetailApiDto = objectMapper.readValue(getUtilisateursResponse.getBody(), new TypeReference<List<UtilisateurIamDetailApiDto>>() {
                });
                return listUtilisateurIamDetailApiDto
                        .stream()
                        .map(utilisateurIamDetailApiDto -> utilisateurIamApiMapper.mapUtilisateurIamDetailApiDtoToEntite(utilisateurIamDetailApiDto))
                        .toList();
            } catch (JsonProcessingException jsonProcessingException) {
                throw new IamException("IdeFlix - Erreur getUtilisateurs - JsonProcessingException");
            }

        } catch (HttpClientErrorException e) {
            throw new IamException("IdeFlix - Erreur getUtilisateurs - Code retour : " + e.getStatusCode());
        } catch (Exception ex) {

            logger.error("IdeFlix - getUtilisateursIam - Exception non prévue  : "
                    + ex.getMessage()
                    + (ex.getCause() == null ? " - " : " - " + ex.getCause()) + ex.getCause());
            throw new IamException("IdeFlix - getUtilisateursIam");
        }

    }

    @Override
    public void delUtilisateurIam(String email) {

    }
}
