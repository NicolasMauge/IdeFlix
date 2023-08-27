package org.epita.exposition.dto.media;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public class FilmDto {
    @Schema(name = "Identifiant TMDB du film", example = "603")
    private String idTmdb;
    @Schema(name = "Titre du film", example = "Matrix")
    private String titre;
    @Schema(name = "Date de sortie du film", example = "1999-03-30")
    private LocalDate dateSortie;
    @Schema(name = "Durée en minutes", example = "135")
    private int duree;

    @Schema(name = "Chemin de l'affiche dans TMDB au format portrait", example = "/pEoqbqtLc4CcwDUDqxmEDSWpWTZ.jpg")
    private String cheminAffichePortrait;
    @Schema(name = "Chemin de l'affiche dans TMDB au format paysage", example = "/oMsxZEvz9a708d49b6UdZK1KAo5.jpg")
    private String cheminAffichePaysage;

    @Schema(name = "Score TMDB", example = "8")
    private int noteTmdb;

    @Schema(name = "Liste des genres du film")
    private List<GenreDto> genreList;

    public FilmDto() {
    }

    public FilmDto(String idTmdb, String titre, LocalDate dateSortie, int duree, String cheminAffichePortrait, String cheminAffichePaysage, int noteTmdb, List<GenreDto> genreList) {
        this.idTmdb = idTmdb;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.duree = duree;
        this.cheminAffichePortrait = cheminAffichePortrait;
        this.cheminAffichePaysage = cheminAffichePaysage;
        this.noteTmdb = noteTmdb;
        this.genreList = genreList;
    }

    public String getIdTmdb() {
        return idTmdb;
    }

    public void setIdTmdb(String idTmdb) {
        this.idTmdb = idTmdb;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getCheminAffichePortrait() {
        return cheminAffichePortrait;
    }

    public void setCheminAffichePortrait(String cheminAffichePortrait) {
        this.cheminAffichePortrait = cheminAffichePortrait;
    }

    public String getCheminAffichePaysage() {
        return cheminAffichePaysage;
    }

    public void setCheminAffichePaysage(String cheminAffichePaysage) {
        this.cheminAffichePaysage = cheminAffichePaysage;
    }

    public int getNoteTmdb() {
        return noteTmdb;
    }

    public void setNoteTmdb(int noteTmdb) {
        this.noteTmdb = noteTmdb;
    }

    public List<GenreDto> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<GenreDto> genreList) {
        this.genreList = genreList;
    }
}
