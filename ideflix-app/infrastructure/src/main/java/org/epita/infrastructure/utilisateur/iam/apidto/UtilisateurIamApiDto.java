package org.epita.infrastructure.utilisateur.iam.apidto;

public class UtilisateurIamApiDto {
    private String nom;
    private String prenom;
    private String email;
    private String dateCreation;

    public UtilisateurIamApiDto(String nom, String prenom, String email, String dateCreation) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateCreation = dateCreation;
    }

    public UtilisateurIamApiDto() {
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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }
}
