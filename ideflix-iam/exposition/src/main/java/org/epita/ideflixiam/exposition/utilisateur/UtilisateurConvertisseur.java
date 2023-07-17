package org.epita.ideflixiam.exposition.utilisateur;

import org.epita.ideflixiam.application.role.RoleService;
import org.epita.ideflixiam.domaine.RoleEntity;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.epita.ideflixiam.exposition.role.RoleConvertisseur;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class UtilisateurConvertisseur {

    RoleConvertisseur roleConvertisseur;
    RoleService roleService;

    public UtilisateurConvertisseur(RoleConvertisseur roleConvertisseur, RoleService roleService) {
        this.roleConvertisseur = roleConvertisseur;
        this.roleService = roleService;
    }

    public UtilisateurEntity convertirDtoVersEntite(UtilisateurEntreeDto utilisateurEntreeDto) {

        return new UtilisateurEntity(utilisateurEntreeDto.getNom(),
                utilisateurEntreeDto.getPrenom(),
                utilisateurEntreeDto.getEmail());

    }


    public UtilisateurSimpleDto convertirEntiteVersSimpleDto(UtilisateurEntity utilisateurEntity) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateCreation = utilisateurEntity.getDateCreation().format(dateTimeFormatter);


        return new UtilisateurSimpleDto(utilisateurEntity.getNom(),
                utilisateurEntity.getPrenom(),
                utilisateurEntity.getEmail(),
                dateCreation);
    }


    public UtilisateurEntity convertirDetailDtoVersEntite(UtilisateurEntreeDto utilisateurEntreeDto, String roleName) {

        List<RoleEntity> roleEntityList = new ArrayList<>();

        RoleEntity roleEntity = roleService.recupererRoleParNomRole(roleName);

        roleEntityList.add(roleEntity);

        return new UtilisateurEntity(utilisateurEntreeDto.getNom(),
                utilisateurEntreeDto.getPrenom(),
                utilisateurEntreeDto.getEmail(),
                utilisateurEntreeDto.getMotDePasse(),
                roleEntityList
        );

    }

    public UtilisateurDetailDto convertirEntiteVersDetailDto(UtilisateurEntity utilisateurEntity) {

        return new UtilisateurDetailDto(utilisateurEntity.getNom(),
                utilisateurEntity.getPrenom(),
                utilisateurEntity.getEmail(),
                utilisateurEntity.getListeRoles()
                        .stream()
                        .map(role -> roleConvertisseur.convertirRoleVersRoleDto(role))
                        .toList());

    }
}
