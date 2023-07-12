package org.epita.ideflixiam.exposition.utilisateuriam;

import org.epita.ideflixiam.application.role.RoleService;
import org.epita.ideflixiam.domaine.RoleEntity;
import org.epita.ideflixiam.domaine.UtilisateurIamEntity;
import org.epita.ideflixiam.exposition.role.RoleConvertisseur;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UtilisateurIamConvertisseur {

    RoleConvertisseur roleConvertisseur;
    RoleService roleService;

    public UtilisateurIamConvertisseur(RoleConvertisseur roleConvertisseur, RoleService roleService) {
        this.roleConvertisseur = roleConvertisseur;
        this.roleService = roleService;
    }

    public UtilisateurIamEntity convertirDtoVersEntite(UtilisateurIamEntreeDto utilisateurIamEntreeDto) {

        return new UtilisateurIamEntity(utilisateurIamEntreeDto.getNom(),
                utilisateurIamEntreeDto.getPrenom(),
                utilisateurIamEntreeDto.getEmail());

    }


    public UtilisateurIamSimpleDto convertirEntiteVersSimpleDto(UtilisateurIamEntity utilisateurIamEntity) {

        return new UtilisateurIamSimpleDto(utilisateurIamEntity.getNom(),
                utilisateurIamEntity.getPrenom(),
                utilisateurIamEntity.getEmail());
    }


    public UtilisateurIamEntity convertirDetailDtoVersEntite(UtilisateurIamEntreeDto utilisateurIamEntreeDto, String roleName) {

        List<RoleEntity> roleEntityList = new ArrayList<>();

        RoleEntity roleEntity = roleService.recupererRoleParNomRole(roleName);
        // TODO gérer ici une exception si on ne trouve pas le rôle

        roleEntityList.add(roleEntity);

        return new UtilisateurIamEntity(utilisateurIamEntreeDto.getNom(),
                utilisateurIamEntreeDto.getPrenom(),
                utilisateurIamEntreeDto.getEmail(),
                utilisateurIamEntreeDto.getMotDePasse(),
                roleEntityList
        );

    }

    public UtilisateurIamDetailDto convertirEntiteVersDetailDto(UtilisateurIamEntity utilisateurIamEntity) {

        return new UtilisateurIamDetailDto(utilisateurIamEntity.getNom(),
                utilisateurIamEntity.getPrenom(),
                utilisateurIamEntity.getEmail(),
                utilisateurIamEntity.getListeRoles()
                        .stream()
                        .map(role -> roleConvertisseur.convertirRoleVersRoleDto(role))
                        .toList());

    }
}
