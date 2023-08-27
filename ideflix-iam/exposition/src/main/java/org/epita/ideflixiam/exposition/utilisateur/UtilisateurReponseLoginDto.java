package org.epita.ideflixiam.exposition.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;
import org.epita.ideflixiam.exposition.role.RoleDto;

import java.util.List;

public class UtilisateurReponseLoginDto {

    @Schema(title = "Nom utilisateur", description = "Nom de l'utilisateur", example = "Dupont")
    private String nom;
    @Schema(title = "Prénom utilisateur", description = "Prénom de l'utilisateur", example = "Charles")
    private String prenom;
    @Schema(title = "Email utilisateur", description = "Email de l'utilisateur", example = "charles.dupont@example.org")
    private String email;

    @Schema(title = "Roles utilisateur", description = "Liste des rôles de l'utilisateur")
    private List<RoleDto> listeRoles;

    @Schema(title = "JSON Web Token", description = "JSON Web Token calculé par IdeFlix IAM et qui devra être envoyé dans toutes les autres requêtes par IdeFlix APP.",
            example = "eyJhbGci1234567890123456cCIxIkpXVCJ9.eyJzdWIiOiJwMUBmciabcdefghijklpbIlJPTEVfVVRJTABCDEFGHIIiXX0.2YWpe3DrYnlcf6ykHYPAaBbCcDdEeFfGgBzTOK7ocEY")
    private String jwt; // on le met dans le body dans la réponse au login suite conseil Frédéric Lossignol et techlead

    public UtilisateurReponseLoginDto(String nom, String prenom, String email, List<RoleDto> listeRoles, String jwt) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.listeRoles = listeRoles;
        this.jwt = jwt;
    }

    public UtilisateurReponseLoginDto() {
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
