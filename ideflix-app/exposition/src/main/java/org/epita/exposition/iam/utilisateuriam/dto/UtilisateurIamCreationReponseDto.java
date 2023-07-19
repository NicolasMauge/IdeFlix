package org.epita.exposition.iam.utilisateuriam.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UtilisateurIamCreationReponseDto {

    @Schema(name = "Nom de l'utilisateur", example = "Dupont", required = true)
    private String nom;
    @Schema(name = "Prénom de l'utilisateur.", example = "Charles", required = true)
    private String prenom;
    @Schema(name = "Email de l'utilisateur.", example = "charles.dupont@example.org", required = true)
    private String email;
    @Schema(name = "Date de création de l'utilisateur dans l'IAM au format dd-MM-yyyy HH:mm:ss.", example = "23/06/2023 14:45:33", required = true)
    private String dateCreation;

    public UtilisateurIamCreationReponseDto(String nom, String prenom, String email, String dateCreation) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateCreation = dateCreation;
    }

    public UtilisateurIamCreationReponseDto() {
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
