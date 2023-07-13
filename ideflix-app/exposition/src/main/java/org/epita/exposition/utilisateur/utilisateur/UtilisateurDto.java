package org.epita.exposition.utilisateur.utilisateur;

import java.time.LocalDate;

public class UtilisateurDto {
    private Long id;

    private String email;

    private String nom;

    private String prenom;

    private LocalDate dateCreation;

    public UtilisateurDto() {
    }

    public UtilisateurDto(Long id, String email, String nom, String prenom, LocalDate dateCreation) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}
