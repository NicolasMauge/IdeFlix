package org.epita.exposition.iam.utilisateuriam.dto;

public class RoleIamDto {
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
