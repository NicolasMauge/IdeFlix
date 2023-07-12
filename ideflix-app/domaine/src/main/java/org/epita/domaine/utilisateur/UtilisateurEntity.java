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

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateCreation;

    @OneToOne(cascade = CascadeType.PERSIST)
    private PreferencesUtilisateurEntity preferencesUtilisateurEntity;

    public UtilisateurEntity() {
    }

    public UtilisateurEntity(String email, LocalDate dateCreation, PreferencesUtilisateurEntity preferencesUtilisateurEntity) {
        this.email = email;
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
