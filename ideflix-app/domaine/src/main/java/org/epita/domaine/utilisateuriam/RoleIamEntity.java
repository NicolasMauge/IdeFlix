package org.epita.domaine.utilisateuriam;

// Pas de stockage donc pas de @Entity
public class RoleIamEntity {
    private String nomRole;

    public RoleIamEntity(String nomRole) {
        this.nomRole = nomRole;
    }

    public RoleIamEntity() {
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
