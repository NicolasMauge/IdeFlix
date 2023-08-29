package org.epita.infrastructure.utilisateur.iam.apidto;

import javax.validation.constraints.Email;

/**
 * Cette classe décrit les données fournies à l'IAM par l'APP lors de la création d'un utilisateur.
 */
public class UtilisateurIamCreationApiDto {
    @Email(message = "email invalide", regexp = "^[a-zA-Z0-9_!#&*+.-]+@[a-zA-Z0-9.-]+$")
    private String nom;
    private String prenom;
    private String email;
    private String motDePasseChiffre;

    /**
     * @param nom
     * @param prenom
     * @param email
     * @param motDePasseChiffre Le mot de passe est chiffré par l'APP avant d'être transmis à l'IAM.
     */
    public UtilisateurIamCreationApiDto(String nom, String prenom, String email, String motDePasseChiffre) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasseChiffre = motDePasseChiffre;
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

    public String getMotDePasseChiffre() {
        return motDePasseChiffre;
    }

    public void setMotDePasseChiffre(String motDePasseChiffre) {
        this.motDePasseChiffre = motDePasseChiffre;
    }
}
