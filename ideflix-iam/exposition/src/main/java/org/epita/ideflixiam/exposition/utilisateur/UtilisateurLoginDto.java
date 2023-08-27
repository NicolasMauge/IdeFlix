package org.epita.ideflixiam.exposition.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;

public class UtilisateurLoginDto {
    @Schema(title = "Email utilisateur", description = "Email de l'utilisateur", example = "charles.dupont@example.org", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(title = "Mot de passe utilisateur", description = "Mot de passe de l'utilisateur", example = "F6565q$$CBS.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String motDePasse;

    public UtilisateurLoginDto(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public UtilisateurLoginDto() {
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
