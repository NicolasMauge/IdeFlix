package org.epita.domaine.utilisateuriam;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

// pas de stockage donc pas de @Entity
public class UtilisateurIamEntity {
    private String email;
    private String nom;
    private String prenom;
    private String motDePasse;

    private boolean isActif; // nécessaire pour Spring Security même si nous ne gérons pas le verrouillage de compte

    @DateTimeFormat(pattern = "yyyy-MM-ss hh:mm:ss")
    private LocalDateTime dateCreation;

    private List<RoleIamEntity> listeRoleIamEntity;

    private String jwt;

    public UtilisateurIamEntity(String email, String nom, String prenom, String motDePasse, boolean isActif, LocalDateTime dateCreation, List<RoleIamEntity> listeRoleIamEntity, String jwt) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.isActif = isActif;
        this.dateCreation = dateCreation;
        this.listeRoleIamEntity = listeRoleIamEntity;
        this.jwt = jwt;
    }

    public UtilisateurIamEntity(String email, String nom, String prenom, String motDePasse) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.isActif = true;
    }

    public UtilisateurIamEntity(String email, String nom, String prenom, LocalDateTime dateCreation) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateCreation = dateCreation;
    }

    public UtilisateurIamEntity(String email, String motDePasse) { // utilisé pour le login
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public UtilisateurIamEntity() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public boolean isActif() {
        return isActif;
    }

    public void setActif(boolean actif) {
        isActif = actif;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<RoleIamEntity> getListeRoleIamEntity() {
        return listeRoleIamEntity;
    }

    public void setListeRoleIamEntity(List<RoleIamEntity> listeRoleIamEntity) {
        this.listeRoleIamEntity = listeRoleIamEntity;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
