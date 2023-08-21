package org.epita.exposition.iam.utilisateuriam.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class UtilisateurIamDto {
    private String nom;
    @Schema(name = "Prénom de l'utilisateur.", example = "Charles", required = true)
    private String prenom;
    @Schema(name = "Email de l'utilisateur", example = "charles.dupont@example.org", required = true)
    private String email;

    private List<RoleIamDto> roles;

    public UtilisateurIamDto(String nom, String prenom, String email, List<RoleIamDto> roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.roles = roles;
    }

    public UtilisateurIamDto() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleIamDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleIamDto> roles) {
        this.roles = roles;
    }
}
