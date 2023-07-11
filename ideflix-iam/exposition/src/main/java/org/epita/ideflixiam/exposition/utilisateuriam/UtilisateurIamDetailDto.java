package org.epita.ideflixiam.exposition.utilisateuriam;

import org.epita.ideflixiam.exposition.role.RoleDto;

import java.util.List;

public class UtilisateurIamDetailDto {

    private String nom;
    private String prenom;
    private String email;

    private String motDePasse;

    private List<RoleDto> listeRoles;

    public UtilisateurIamDetailDto() {
    }

    public UtilisateurIamDetailDto(String nom, String prenom, String email, String motDePasse, List<RoleDto> listeRoles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.listeRoles = listeRoles;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public List<RoleDto> getListeRoles() {
        return listeRoles;
    }
}
