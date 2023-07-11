package org.epita.ideflixiam.exposition.role;

public class RoleDto {
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
