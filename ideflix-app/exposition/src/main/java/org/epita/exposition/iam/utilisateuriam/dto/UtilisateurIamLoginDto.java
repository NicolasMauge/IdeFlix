package org.epita.exposition.iam.utilisateuriam.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UtilisateurIamLoginDto {

    // DTO utilisé par l'IHM lors du login d'un utilisateur

    @Schema(name = "Email de l'utilisateur", example = "charles.dupont@example.org", required = true)
    String email;

    @Schema(name = "Mot de passe de l'utilisateur", example = "Mdp56045A!", required = true)
    String motDePasse;

    public UtilisateurIamLoginDto(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public UtilisateurIamLoginDto() {
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
