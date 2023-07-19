package org.epita.domaine.utilisateur;

import org.epita.domaine.media.GenreEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PreferencesUtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pseudo;
    @OneToOne
    private UtilisateurEntity utilisateur;

    @ManyToMany
    private List<GenreEntity> genreList;

    public PreferencesUtilisateurEntity() {
    }

    public PreferencesUtilisateurEntity(Long id, String pseudo, UtilisateurEntity utilisateur, List<GenreEntity> genreList) {
        this.id = id;
        this.pseudo = pseudo;
        this.utilisateur = utilisateur;
        this.genreList = genreList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public UtilisateurEntity getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurEntity utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<GenreEntity> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<GenreEntity> genreList) {
        this.genreList = genreList;
    }
}
