package org.epita.domaine.utilisateur;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateCreation;
    @OneToOne(cascade = CascadeType.PERSIST)
    private PreferencesUtilisateurEntity preferencesUtilisateurEntity;

    public UtilisateurEntity() {
    }

    public UtilisateurEntity(Long id, String email, String nom, String prenom, LocalDate dateCreation, PreferencesUtilisateurEntity preferencesUtilisateurEntity) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.dateCreation = dateCreation;
        this.preferencesUtilisateurEntity = preferencesUtilisateurEntity;
    }

    public UtilisateurEntity(String email, LocalDate dateCreation) {
        this.email = email;
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

    public PreferencesUtilisateurEntity getPreferencesUtilisateurEntity() {
        return preferencesUtilisateurEntity;
    }

    public void setPreferencesUtilisateurEntity(PreferencesUtilisateurEntity preferencesUtilisateurEntity) {
        this.preferencesUtilisateurEntity = preferencesUtilisateurEntity;
    }
}
