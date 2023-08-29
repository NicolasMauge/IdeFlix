package org.epita.infrastructure.utilisateur.iam.apidto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UtilisateurIamCreationApiDto {
    @Email(message = "email invalide", regexp = "^[a-zA-Z0-9_!#&*+.-]+@[a-zA-Z0-9.-]+$")
    private String nom;
    private String prenom;
    private String email;
    @NotEmpty(message = "Mot de passe obligatoire")
    private String motDePasse;

    public UtilisateurIamCreationApiDto(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public UtilisateurIamCreationApiDto() {
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
