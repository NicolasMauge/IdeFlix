package org.epita.exposition.iam.utilisateuriam.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class RoleIamDto {
    @Schema(description = "Nom d'un rôle de l'utilisateur.", allowableValues = {"ROLE_ADMIN", "ROLE_UTILISATEUR"})
    String nomRole;

    public RoleIamDto(String nomRole) {
        this.nomRole = nomRole;
    }

    public RoleIamDto() {
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
