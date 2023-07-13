package org.epita.exposition.utilisateur.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.utilisateur.preferences.PreferencesUtilisateurDto;

import java.time.LocalDate;

public class UtilisateurEtPrefDto {
    private Long id;

    private String email;

    private LocalDate dateCreation;

    private PreferencesUtilisateurDto preferencesUtilisateur;

    public UtilisateurEtPrefDto() {
    }

    public UtilisateurEtPrefDto(Long id, String email, LocalDate dateCreation, PreferencesUtilisateurDto preferencesUtilisateur) {
        this.id = id;
        this.email = email;
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
