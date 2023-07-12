package org.epita.ideflixiam.exposition.utilisateuriam;

import org.epita.ideflixiam.exposition.role.RoleDto;

import java.util.List;

public class UtilisateurIamDetailDto {

    private String nom;
    private String prenom;
    private String email;

    private List<RoleDto> listeRoles;

    public UtilisateurIamDetailDto() {
    }

    public UtilisateurIamDetailDto(String nom, String prenom, String email, List<RoleDto> listeRoles) {
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
