package org.epita.exposition.media.film;

import org.epita.exposition.media.genre.GenreDto;

import java.time.LocalDate;
import java.util.List;

public class FilmDto {
    private String idTmdb;
    private String titre;
    private LocalDate dateSortie;
    private int duree;
    private String cheminAffichePortrait;
    private String cheminAffichePaysage;
    private int noteTmdb;
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
