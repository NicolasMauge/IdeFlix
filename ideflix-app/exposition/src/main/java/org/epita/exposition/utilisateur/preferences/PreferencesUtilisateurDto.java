package org.epita.exposition.utilisateur.preferences;

import org.epita.exposition.media.genre.GenreDto;

import java.util.List;

public class PreferencesUtilisateurDto {
    private Long id;
    private String pseudo;
    private List<GenreDto> genreList;

    public PreferencesUtilisateurDto() {
    }

    public PreferencesUtilisateurDto(Long id, String pseudo, List<GenreDto> genreList) {
        this.id = id;
        this.pseudo = pseudo;
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
}
