package org.epita.infrastructure.utilisateur.iam.mapper;

import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamCreationApiDto;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamCreationReponseApiDto;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamLoginApiDto;
import org.epita.infrastructure.utilisateur.iam.apidto.UtilisateurIamLoginReponseApiDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class UtilisateurIamApiMapper {

    RoleIamApiMapper roleIamApiMapper;

    public UtilisateurIamApiMapper(RoleIamApiMapper roleIamApiMapper) {
        this.roleIamApiMapper = roleIamApiMapper;
    }

    public UtilisateurIamCreationApiDto mapEntityToCreationApiDto(UtilisateurIamEntity utilisateurIamEntity) {
        return new UtilisateurIamCreationApiDto(
                utilisateurIamEntity.getNom(),
                utilisateurIamEntity.getPrenom(),
                utilisateurIamEntity.getEmail(),
                utilisateurIamEntity.getMotDePasse()
        );
    }

    public UtilisateurIamEntity mapCreationReponseDtoToEntity(UtilisateurIamCreationReponseApiDto utilisateurIamCreationReponseApiDto) {

        LocalDateTime dateCreationIam;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        dateCreationIam = LocalDateTime.parse(utilisateurIamCreationReponseApiDto.getDateCreation(),
                dateTimeFormatter);

        return new UtilisateurIamEntity(
                utilisateurIamCreationReponseApiDto.getEmail(),
                utilisateurIamCreationReponseApiDto.getNom(),
                utilisateurIamCreationReponseApiDto.getPrenom(),
                dateCreationIam
        );
    }

    public UtilisateurIamLoginApiDto mapEntiteToLoginApiDto(UtilisateurIamEntity utilisateurIamEntity) {

        return new UtilisateurIamLoginApiDto(
                utilisateurIamEntity.getEmail(),
                utilisateurIamEntity.getMotDePasse()
        );

    }

    public UtilisateurIamEntity mapLoginReponseApiDtoToEntity(UtilisateurIamLoginReponseApiDto utilisateurIamLoginReponseApiDto) {
        return new UtilisateurIamEntity(
                utilisateurIamLoginReponseApiDto.getEmail(),
                utilisateurIamLoginReponseApiDto.getPrenom(),
                utilisateurIamLoginReponseApiDto.getPrenom(),
                utilisateurIamLoginReponseApiDto
                        .getListeRoles()
                        .stream()
                        .map(roleIamApiDto -> roleIamApiMapper.mapApiDtoToEntity(roleIamApiDto))
                        .toList(),
                utilisateurIamLoginReponseApiDto.getJwt()
        );

    }
}
