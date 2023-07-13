package org.epita.exposition.utilisateur.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.utilisateur.preferences.PreferencesUtilisateurDto;

import java.time.LocalDate;

public class UtilisateurEtPrefDto {
    private Long id;

    private String email;

    private String nom;

    private String prenom;

    private LocalDate dateCreation;

    private PreferencesUtilisateurDto preferencesUtilisateur;

    public UtilisateurEtPrefDto() {
    }

    public UtilisateurEtPrefDto(Long id, String email, String nom, String prenom, LocalDate dateCreation, PreferencesUtilisateurDto preferencesUtilisateur) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateCreation = dateCreation;
        this.preferencesUtilisateur = preferencesUtilisateur;
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

    public PreferencesUtilisateurDto getPreferencesUtilisateur() {
        return preferencesUtilisateur;
    }

    public void setPreferencesUtilisateur(PreferencesUtilisateurDto preferencesUtilisateur) {
        this.preferencesUtilisateur = preferencesUtilisateur;
    }
}