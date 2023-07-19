package org.epita.infrastructure.utilisateur.iam.apidto;

import java.util.List;

public class UtilisateurIamLoginReponseApiDto {
    private String nom;
    private String prenom;
    private String email;

    private List<RoleIamApiDto> listeRoleIamApiDto;

    private String jwt; // on le met dans le body dans la réponse au login suite conseil Frédéric Lossignol et techlead

    public UtilisateurIamLoginReponseApiDto(String nom, String prenom, String email, List<RoleIamApiDto> listeRoleIamApiDto, String jwt) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.listeRoleIamApiDto = listeRoleIamApiDto;
        this.jwt = jwt;
    }

    public UtilisateurIamLoginReponseApiDto() {
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

    public List<RoleIamApiDto> getListeRoleIamApiDto() {
        return listeRoleIamApiDto;
    }

    public void setListeRoleIamApiDto(List<RoleIamApiDto> listeRoleIamApiDto) {
        this.listeRoleIamApiDto = listeRoleIamApiDto;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
