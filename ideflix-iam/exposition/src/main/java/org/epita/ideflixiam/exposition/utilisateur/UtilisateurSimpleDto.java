package org.epita.ideflixiam.exposition.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;

public class UtilisateurSimpleDto {


    @Schema(title = "Nom utilisateur", description = "Nom de l'utilisateur", example = "Dupont", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private String nom;
    @Schema(title = "Prénom utilisateur", description = "Prénom de l'utilisateur", example = "Charles", requiredMode = Schema.RequiredMode.REQUIRED)
    private String prenom;
    @Schema(title = "Email utilisateur", description = "Email de l'utilisateur", example = "charles.dupont@example.org", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(title = "Date création utilisateur", description = "Date de création de l'utilisateur", example = "27-08-2023 17:38:37", requiredMode = Schema.RequiredMode.REQUIRED)
    private String dateCreation;

    public UtilisateurSimpleDto() {

    }

    public UtilisateurSimpleDto(String nom, String prenom, String email, String dateCreation) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateCreation = dateCreation;
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
