package org.epita.ideflixiam.exposition.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;
import org.epita.ideflixiam.exposition.role.RoleDto;

import java.util.List;

public class UtilisateurDetailDto {

    @Schema(title = "Nom utilisateur", description = "Nom de l'utilisateur", example = "Dupont", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;
    @Schema(title = "Prénom utilisateur", description = "Prénom de l'utilisateur", example = "Charles", requiredMode = Schema.RequiredMode.REQUIRED)
    private String prenom;
    @Schema(title = "Email utilisateur", description = "Email de l'utilisateur", example = "charles.dupont@example.org", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(title = "Roles utilisateur", description = "Liste des rôles de l'utilisateur", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<RoleDto> listeRoles;

    public UtilisateurDetailDto() {
    }

    public UtilisateurDetailDto(String nom, String prenom, String email, List<RoleDto> listeRoles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.listeRoles = listeRoles;
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

    public List<RoleDto> getListeRoles() {
        return listeRoles;
    }

    public void setListeRoles(List<RoleDto> listeRoles) {
        this.listeRoles = listeRoles;
    }
}
