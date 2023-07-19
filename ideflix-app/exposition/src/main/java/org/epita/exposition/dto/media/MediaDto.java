package org.epita.exposition.dto.media;

import org.epita.exposition.dto.media.GenreDto;

import java.time.LocalDate;
import java.util.List;

public class MediaDto {
    private String idTmdb;
    private String titre;
    private LocalDate dateSortie;
    private int duree;
    private String cheminAffichePortrait;
    private String cheminAffichePaysage;
    private int noteTmdb;
    private List<GenreDto> genreList;
    private int nombreSaisons;

    public MediaDto(String idTmdb, String titre, LocalDate dateSortie, int duree, String cheminAffichePortrait, String cheminAffichePaysage, int noteTmdb, List<GenreDto> genreList, int nombreSaisons) {
        this.idTmdb = idTmdb;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.duree = duree;
        this.cheminAffichePortrait = cheminAffichePortrait;
        this.cheminAffichePaysage = cheminAffichePaysage;
        this.noteTmdb = noteTmdb;
        this.genreList = genreList;
        this.nombreSaisons = nombreSaisons;
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

    public int getNombreSaisons() {
        return nombreSaisons;
    }

    public void setNombreSaisons(int nombreSaisons) {
        this.nombreSaisons = nombreSaisons;
    }
}
