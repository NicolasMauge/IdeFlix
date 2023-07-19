package org.epita.infrastructure.utilisateur.iam;

import org.epita.domaine.utilisateuriam.RoleIamEntity;
import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamApiMapper;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamCreationReponseApiDto;
import org.epita.infrastructure.utilisateur.iam.exceptions.IamException;
import org.epita.infrastructure.utilisateur.iam.exceptions.IamUtilisateurExisteDejaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class UtilisateurIamRepositoryImpl implements UtilisateurIamRepository {
    UtilisateurIamApiMapper utilisateurIamApiMapper;

    public UtilisateurIamRepositoryImpl(UtilisateurIamApiMapper utilisateurIamApiMapper) {
        this.utilisateurIamApiMapper = utilisateurIamApiMapper;
    }

    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity nouvelUtilisateurIam) {

        //List<RoleIamEntity> listeRoleIamEntity = new RoleIamEntity[].{new RoleIamEntity("ROLE_UTILISATEUR")};

        final String iamCreationEndpointUrl = "http://localhost:8080/api/v1/iam/utilisateur";

        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uri, String.class);

        ResponseEntity<UtilisateurIamCreationReponseApiDto> creationResponse = restTemplate.postForEntity(iamCreationEndpointUrl,
                utilisateurIamApiMapper.mapEntityToCreationApiDto(nouvelUtilisateurIam),
                UtilisateurIamCreationReponseApiDto.class);

        HttpStatus codeReponseHttp = creationResponse.getStatusCode();

        return switch (codeReponseHttp) {
            case CREATED -> utilisateurIamApiMapper.mapCreationReponseDtoToEntity(creationResponse.getBody());
            case CONFLICT -> throw new IamUtilisateurExisteDejaException("(" + nouvelUtilisateurIam.getEmail() + ").");
            default ->
                    throw new IamException("Code retour : " + codeReponseHttp.name() + " (" + nouvelUtilisateurIam.getEmail() + ").");
        };


    }

    @Override
    public UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIam) {
        return null;
    }

    @Override
    public List<UtilisateurIamEntity> getUtilisateursIam() {
        return null;
    }

    @Override
    public void delUtilisateurIam(String email) {

    }
}
