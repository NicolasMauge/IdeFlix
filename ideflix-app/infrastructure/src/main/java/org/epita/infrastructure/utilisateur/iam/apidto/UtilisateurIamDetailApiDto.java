package org.epita.infrastructure.utilisateur.iam.apidto;

import java.util.List;

public class UtilisateurIamDetailApiDto {

    private String nom;
    private String prenom;
    private String email;

    private List<RoleIamApiDto> listeRoles;

    public UtilisateurIamDetailApiDto(String nom, String prenom, String email, List<RoleIamApiDto> listeRoles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.listeRoles = listeRoles;
    }

    public UtilisateurIamDetailApiDto() {
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

    public List<RoleIamApiDto> getListeRoles() {
        return listeRoles;
    }

    public void setListeRoles(List<RoleIamApiDto> listeRoles) {
        this.listeRoles = listeRoles;
    }
}
