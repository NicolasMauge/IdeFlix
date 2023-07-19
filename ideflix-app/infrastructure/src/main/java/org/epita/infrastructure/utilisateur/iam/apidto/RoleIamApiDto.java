package org.epita.infrastructure.utilisateur.iam.apidto;

public class RoleIamApiDto {
    private String nomRole;

    public RoleIamApiDto(String nomRole) {
        this.nomRole = nomRole;
    }

    public RoleIamApiDto() {
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
