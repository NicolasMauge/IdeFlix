package org.epita.exposition.iam.utilisateuriam.dto;

import java.util.List;

public class UtilisateurIamLoginReponseDto {
    private String nom;
    private String prenom;
    private String email;

    private List<RoleIamDto> listeRoleIamDto;

    private String jwt; // on le met dans le body dans la réponse au login suite conseil Frédéric Lossignol et techlead

    public UtilisateurIamLoginReponseDto(String nom, String prenom, String email, List<RoleIamDto> listeRoleIamDto, String jwt) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.listeRoleIamDto = listeRoleIamDto;
        this.jwt = jwt;
    }

    public UtilisateurIamLoginReponseDto() {
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

    public List<RoleIamDto> getListeRoleIamDto() {
        return listeRoleIamDto;
    }

    public void setListeRoleIamDto(List<RoleIamDto> listeRoleIamDto) {
        this.listeRoleIamDto = listeRoleIamDto;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
