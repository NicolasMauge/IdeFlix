package org.epita.infrastructure.utilisateur.iam.apidto;

public class UtilisateurIamLoginApiDto {

    private String email;
    private String motDePasse;

    public UtilisateurIamLoginApiDto(String email, String motDePasse) {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public UtilisateurIamLoginApiDto() {
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
