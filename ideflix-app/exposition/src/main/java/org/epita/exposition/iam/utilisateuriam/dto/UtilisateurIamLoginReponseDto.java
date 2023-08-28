package org.epita.exposition.iam.utilisateuriam.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class UtilisateurIamLoginReponseDto {
    private String nom;
    @Schema(description = "Prénom de l'utilisateur.", example = "Charles",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String prenom;
    @Schema(description = "Email de l'utilisateur", example = "charles.dupont@example.org",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Liste des rôles de l'utilisateur", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<RoleIamDto> listeRoleIamDto;

    @Schema(description = "Token de l'utilisateur. Il faudra ensuite mettre se token précédé du mot Bearer dans l'en-tête Authorization de la plupart des requêtes.",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String jwt; // on le met dans le body dans la réponse au login suite conseil Frédéric Lossignol et techlead

    public UtilisateurIamLoginReponseDto(String nom, String prenom, String email, List<RoleIamDto> listeRoleIamDto, String jwt) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.listeRoleIamDto = listeRoleIamDto;
        this.jwt = jwt;
    }

    public UtilisateurIamLoginReponseDto() {
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

    public List<RoleIamDto> getListeRoleIamDto() {
        return listeRoleIamDto;
    }

    public void setListeRoleIamDto(List<RoleIamDto> listeRoleIamDto) {
        this.listeRoleIamDto = listeRoleIamDto;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
