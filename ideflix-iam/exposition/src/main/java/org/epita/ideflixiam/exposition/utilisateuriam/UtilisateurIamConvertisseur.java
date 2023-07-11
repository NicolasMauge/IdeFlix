package org.epita.ideflixiam.exposition.utilisateuriam;

import org.epita.ideflixiam.domaine.UtilisateurIam;
import org.epita.ideflixiam.exposition.role.RoleConvertisseur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurIamConvertisseur {

    RoleConvertisseur roleConvertisseur;

    public UtilisateurIamConvertisseur(RoleConvertisseur roleConvertisseur) {
        this.roleConvertisseur = roleConvertisseur;
    }

    public UtilisateurIam convertirDtoVersEntite(UtilisateurIamDto utilisateurIamDto) {

        return new UtilisateurIam(utilisateurIamDto.getNom(),
                utilisateurIamDto.getPrenom(),
                utilisateurIamDto.getEmail());

    }


    public UtilisateurIamDto convertirEntiteVersDto(UtilisateurIam utilisateurIam) {

        return new UtilisateurIamDto(utilisateurIam.getNom(),
                utilisateurIam.getPrenom(),
                utilisateurIam.getEmail(),
                utilisateurIam.getListeRoles()
                        .stream()
                        .map(role -> this.roleConvertisseur.convertirRoleVersRoleDto(role))
                        .toList());


    }


    public UtilisateurIam convertirDetailDtoVersEntite(UtilisateurIamDetailDto utilisateurIamDetailDto) {

        return new UtilisateurIam(utilisateurIamDetailDto.getNom(),
                utilisateurIamDetailDto.getPrenom(),
                utilisateurIamDetailDto.getEmail(),
                utilisateurIamDetailDto.getMotDePasse(),
                utilisateurIamDetailDto.getListeRoles()
                        .stream()
                        .map(roleDto -> this.roleConvertisseur.convertirRoleDtoVersRole(roleDto))
                        .toList()
        );

    }


}
