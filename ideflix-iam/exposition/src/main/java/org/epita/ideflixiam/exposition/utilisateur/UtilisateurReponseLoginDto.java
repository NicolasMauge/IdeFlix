package org.epita.ideflixiam.exposition.utilisateur;

import org.epita.ideflixiam.exposition.role.RoleDto;

import java.util.List;

public class UtilisateurReponseLoginDto {

    private String nom;
    private String prenom;
    private String email;

    private List<RoleDto> listeRoles;

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
