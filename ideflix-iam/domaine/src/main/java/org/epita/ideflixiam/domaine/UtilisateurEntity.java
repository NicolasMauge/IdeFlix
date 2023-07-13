package org.epita.ideflixiam.domaine;

import javax.persistence.*;
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

    private boolean isActif;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<RoleEntity> listeRoleEntities;

    public UtilisateurEntity() {
    }

    public UtilisateurEntity(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.isActif = true;
    }

    public UtilisateurEntity(String nom, String prenom, String email, String motDePasse, List<RoleEntity> listeRoleEntities) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.listeRoleEntities = listeRoleEntities;
        this.isActif = true;
    }

    public UtilisateurEntity(Long id) {
        this.id = id;
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
