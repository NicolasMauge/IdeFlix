package org.epita.ideflixiam.domaine;

import javax.persistence.*;
import java.util.List;

@Entity
public class UtilisateurIam {
    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;
    private String email;

    private String motDePasse;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> listeRoles;

    public UtilisateurIam() {
    }

    public UtilisateurIam(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public UtilisateurIam(String nom, String prenom, String email, String motDePasse, List<Role> listeRoles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.listeRoles = listeRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Role> getListeRoles() {
        return listeRoles;
    }

    public void setListeRoles(List<Role> listeRoles) {
        this.listeRoles = listeRoles;
    }
}
