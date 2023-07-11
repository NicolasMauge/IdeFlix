package org.epita.domaine.utilisateur;

import org.epita.domaine.media.Genre;

import javax.persistence.*;
import java.util.List;

@Entity
public class PreferencesUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Genre> genreList;

    public PreferencesUtilisateur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }
}
