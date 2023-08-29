package org.epita.infrastructure.utilisateur.iam.apidto;

/**
 * Cette classe est utilisée par l'APP en entrée de l'IAM lors du login de l'utilisateur.
 */
public class UtilisateurIamLoginApiDto {

    private String email;
    private String motDePasseChiffre;

    /**
     * @param email
     * @param motDePasseChiffre Le mot de passe est chiffré par l'APP avant d'être transmis à l'IAM. (voir la classe Chiffreur.class)
     */
    public UtilisateurIamLoginApiDto(String email, String motDePasseChiffre) {
        this.email = email;
        this.motDePasseChiffre = motDePasseChiffre;
    }

    public UtilisateurIamLoginApiDto() {
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
