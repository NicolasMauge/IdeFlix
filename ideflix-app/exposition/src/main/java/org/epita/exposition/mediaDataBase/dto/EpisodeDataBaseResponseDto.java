package org.epita.exposition.mediaDataBase.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class EpisodeDataBaseResponseDto {

    // DTO en réponse vers IHM

    @Schema(name = "date de sortie de l'épisode'", example = "2008-02-20", required = true)
    private String dateSortie;
    private int numeroEpisodes;

    private int duree;

    @Schema(name = "identifiant de la movie Data Base", example = "693", required = true)
    private long idDataBase;
    private String titre;
    private String resume;
    private String cheminAffichePortrait;
    private int numeroSaison;
    private float noteDataBase;

    public EpisodeDataBaseResponseDto() {
    }

    public EpisodeDataBaseResponseDto(String dateSortie, int numeroEpisodes, int duree, long idDataBase, String titre, String resume, String cheminAffichePortrait, int numeroSaison, float noteDataBase) {
        this.dateSortie = dateSortie;
        this.numeroEpisodes = numeroEpisodes;
        this.duree = duree;
        this.idDataBase = idDataBase;
        this.titre = titre;
        this.resume = resume;
        this.cheminAffichePortrait = cheminAffichePortrait;
        this.numeroSaison = numeroSaison;
        this.noteDataBase = noteDataBase;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getNumeroEpisodes() {
        return numeroEpisodes;
    }

    public void setNumeroEpisodes(int numeroEpisodes) {
        this.numeroEpisodes = numeroEpisodes;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
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

    public int getNumeroSaison() {
        return numeroSaison;
    }

    public void setNumeroSaison(int numeroSaison) {
        this.numeroSaison = numeroSaison;
    }

    public float getNoteDataBase() {
        return noteDataBase;
    }

    public void setNoteDataBase(float noteDataBase) {
        this.noteDataBase = noteDataBase;
    }
}
