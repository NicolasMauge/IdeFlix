package org.epita.ideflixiam.exposition.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;

public class UtilisateurEntreeDto {

    @Schema(name = "Nom de l'utilisateur", example = "Dupont", required = true)
    private String nom;
    @Schema(name = "Prénom de l'utilisateur", example = "Charles", required = true)
    private String prenom;
    @Schema(name = "Email de l'utilisateur", example = "charles.dupont@example.org", required = true)
    private String email;

    @Schema(name = "Mot de passe de l'utilisateur", example = "F6565q-(SSskjk5jnkj)CBS.", required = true)
    private String motDePasse;

    public UtilisateurEntreeDto() {
    }

    public UtilisateurEntreeDto(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
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
