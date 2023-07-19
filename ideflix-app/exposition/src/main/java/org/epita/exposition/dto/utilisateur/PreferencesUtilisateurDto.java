package org.epita.exposition.dto.utilisateur;

import org.epita.exposition.dto.media.GenreDto;

import java.util.List;

public class PreferencesUtilisateurDto {
    private String pseudo;
    private String email;

    private List<GenreDto> genreList;

    public PreferencesUtilisateurDto() {
    }

    public PreferencesUtilisateurDto(String pseudo, String email, List<GenreDto> genreList) {
        this.pseudo = pseudo;
        this.email = email;
        this.genreList = genreList;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GenreDto> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<GenreDto> genreList) {
        this.genreList = genreList;
    }
}
