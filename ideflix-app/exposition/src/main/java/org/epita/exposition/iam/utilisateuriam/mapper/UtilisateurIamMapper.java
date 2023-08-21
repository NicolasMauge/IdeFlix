package org.epita.exposition.iam.utilisateuriam.mapper;

import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.exposition.iam.utilisateuriam.dto.*;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class UtilisateurIamMapper { // Ici, on n'étend pas la classe Mapper
    // car uniquement des conversions partielles selon le contexte

    RoleIamMapper roleIamMapper;

    public UtilisateurIamMapper(RoleIamMapper roleIamMapper) {
        this.roleIamMapper = roleIamMapper;
    }

    // Cas du login :
    public UtilisateurIamEntity mapLoginDtoToEntity(UtilisateurIamLoginDto utilisateurIamLoginDto) {
        return new UtilisateurIamEntity(
                utilisateurIamLoginDto.getEmail(),
                utilisateurIamLoginDto.getMotDePasse()
        );
    }

    public UtilisateurIamLoginReponseDto mapEntityToLoginReponseDto(UtilisateurIamEntity utilisateurIamEntity) {
        return new UtilisateurIamLoginReponseDto(
                utilisateurIamEntity.getNom(),
                utilisateurIamEntity.getPrenom(),
                utilisateurIamEntity.getEmail(),
                utilisateurIamEntity.getListeRoleIamEntity().stream().map(roleIamEntity -> roleIamMapper.mapEntityToDto(roleIamEntity)).toList(),
                utilisateurIamEntity.getJwt()
        );
    }


    // Cas de la création d'utilisateur
    public UtilisateurIamEntity mapCreationDtoToEntity(UtilisateurIamCreationDto utilisateurIamCreationDto) {
        return new UtilisateurIamEntity(
                utilisateurIamCreationDto.getEmail(),
                utilisateurIamCreationDto.getNom(),
                utilisateurIamCreationDto.getPrenom(),
                utilisateurIamCreationDto.getMotDePasse()
        );
    }

    public UtilisateurIamCreationReponseDto mapEntityToCreationResponseDto(UtilisateurIamEntity utilisateurIamEntity) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateCreation = utilisateurIamEntity.getDateCreation().format(dateTimeFormatter);

        return new UtilisateurIamCreationReponseDto(
                utilisateurIamEntity.getEmail(),
                utilisateurIamEntity.getNom(),
                utilisateurIamEntity.getPrenom(),
                dateCreation
        );

    }

    public List<UtilisateurIamDto> mapEntityListToUtilisateurIamDto(List<UtilisateurIamEntity> utilisateursIam) {


        return utilisateursIam
                .stream()
                .map(this::mapUtilisateurIamEntityToUtilisateurIamDto)
                .toList();


    }

    private UtilisateurIamDto mapUtilisateurIamEntityToUtilisateurIamDto(UtilisateurIamEntity utilisateurIam) {

        return new UtilisateurIamDto(utilisateurIam.getNom(),
                utilisateurIam.getPrenom(),
                utilisateurIam.getEmail(),
                roleIamMapper.mapListEntityToDto(utilisateurIam.getListeRoleIamEntity()));

    }
}
