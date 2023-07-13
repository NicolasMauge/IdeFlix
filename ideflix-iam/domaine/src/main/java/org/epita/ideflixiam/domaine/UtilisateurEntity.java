package org.epita.ideflixiam.domaine;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String nom;
    private String prenom;

    private String motDePasse;

    private boolean isActif; // nécessaire pour Spring Security même si nous ne gérons pas le verrouillage de compte

    @DateTimeFormat(pattern = "yyyy-MM-ss hh:mm:ss")
    private LocalDateTime dateCreation;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<RoleEntity> listeRoleEntities;

    public UtilisateurEntity() {
    }

    public UtilisateurEntity(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.isActif = true;
        this.dateCreation = LocalDateTime.now();
    }

    public UtilisateurEntity(String nom, String prenom, String email, String motDePasse, List<RoleEntity> listeRoleEntities) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.listeRoleEntities = listeRoleEntities;
        this.isActif = true;
        this.dateCreation = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<RoleEntity> getListeRoleEntities() {
        return listeRoleEntities;
    }

    public void setListeRoleEntities(List<RoleEntity> listeRoleEntities) {
        this.listeRoleEntities = listeRoleEntities;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<RoleEntity> getListeRoles() {
        return listeRoleEntities;
    }

    public void setListeRoles(List<RoleEntity> listeRoleEntities) {
        this.listeRoleEntities = listeRoleEntities;
    }


}
