package org.epita.domaine.mediaDataBase;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class MediaAudioVisuelDataBase {

    private long idDataBase;

    private String titre;

    private List<GenreDataBase> genres;

    private String resume;

    private String cheminAffichePortrait;

    private String CheminAffichePaysage;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateSortie;

    private int duree;

    private float noteDataBase;

    public MediaAudioVisuelDataBase() {
    }

    public MediaAudioVisuelDataBase(long idDataBase, String titre, List<GenreDataBase> genres, String resume, String cheminAffichePortrait, String cheminAffichePaysage, LocalDate dateSortie, int duree, float noteDataBase) {
        this.idDataBase = idDataBase;
        this.titre = titre;
        this.genres = genres;
        this.resume = resume;
        this.cheminAffichePortrait = cheminAffichePortrait;
        CheminAffichePaysage = cheminAffichePaysage;
        this.dateSortie = dateSortie;
        this.duree = duree;
        this.noteDataBase = noteDataBase;
    }

    public long getIdDataBase() {
        return idDataBase;
    }

    public void setIdDataBase(long idDataBase) {
        this.idDataBase = idDataBase;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<GenreDataBase> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDataBase> genres) {
        this.genres = genres;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCheminAffichePortrait() {
        return cheminAffichePortrait;
    }

    public void setCheminAffichePortrait(String cheminAffichePortrait) {
        this.cheminAffichePortrait = cheminAffichePortrait;
    }

    public String getCheminAffichePaysage() {
        return CheminAffichePaysage;
    }

    public void setCheminAffichePaysage(String cheminAffichePaysage) {
        CheminAffichePaysage = cheminAffichePaysage;
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

    public float getNoteDataBase() {
        return noteDataBase;
    }

    public void setNoteDataBase(float noteDataBase) {
        this.noteDataBase = noteDataBase;
    }

    @Override
    public String toString() {
        return "MediaAudioVisuelDataBase{" +
                "idDataBase=" + idDataBase +
                ", titre='" + titre + '\'' +
                ", genres=" + genres +
                ", resume='" + resume + '\'' +
                ", cheminAffichePortrait='" + cheminAffichePortrait + '\'' +
                ", CheminAffichePaysage='" + CheminAffichePaysage + '\'' +
                ", dateSortie=" + dateSortie +
                ", duree=" + duree +
                ", noteDataBase=" + noteDataBase +
                '}';
    }
}
