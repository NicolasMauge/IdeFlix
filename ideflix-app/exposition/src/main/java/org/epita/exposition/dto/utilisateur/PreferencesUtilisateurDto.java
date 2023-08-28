package org.epita.exposition.dto.utilisateur;

import io.swagger.v3.oas.annotations.media.Schema;
import org.epita.exposition.dto.media.GenreDto;

import java.util.List;

public class PreferencesUtilisateurDto {
    @Schema(description = "Pseudo choisi par l'utilisateur.")
    private String pseudo;
    @Schema(description = "Email de l'utilisateur.")
    private String email;
    @Schema(description = "Liste des genres de séries et de films choisis par l'utilisateur.")
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
