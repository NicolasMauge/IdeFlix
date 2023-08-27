package org.epita.ideflixiam.exposition.role;

import io.swagger.v3.oas.annotations.media.Schema;

public class RoleDto {

    @Schema(title = "Rôle", description = "Nom d'un rôle", requiredMode = Schema.RequiredMode.REQUIRED, example = "ROLE_UTILISATEUR")
    private String nomRole;


    public RoleDto() {
    }

    public RoleDto(String nomRole) {
        this.nomRole = nomRole;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
