package org.epita.domaine.utilisateur;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private LocalDate dateCreation;

    @OneToOne
    private PreferencesUtilisateurEntity preferencesUtilisateurEntity;

    public UtilisateurEntity() {
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

    public PreferencesUtilisateurEntity getPreferencesUtilisateur() {
        return preferencesUtilisateurEntity;
    }

    public void setPreferencesUtilisateur(PreferencesUtilisateurEntity preferencesUtilisateurEntity) {
        this.preferencesUtilisateurEntity = preferencesUtilisateurEntity;
    }
}