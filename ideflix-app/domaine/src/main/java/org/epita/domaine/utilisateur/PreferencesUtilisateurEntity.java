package org.epita.domaine.utilisateur;

import org.epita.domaine.media.GenreEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class PreferencesUtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<GenreEntity> genreEntityList;

    private String pseudo;

    public PreferencesUtilisateurEntity() {
    }

    public PreferencesUtilisateurEntity(Long id, List<GenreEntity> genreEntityList, String pseudo) {
        this.id = id;
        this.genreEntityList = genreEntityList;
        this.pseudo = pseudo;
    }

    public PreferencesUtilisateurEntity(String pseudo) {
        this.pseudo = pseudo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GenreEntity> getGenreList() {
        return genreEntityList;
    }

    public void setGenreList(List<GenreEntity> genreEntityList) {
        this.genreEntityList = genreEntityList;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
