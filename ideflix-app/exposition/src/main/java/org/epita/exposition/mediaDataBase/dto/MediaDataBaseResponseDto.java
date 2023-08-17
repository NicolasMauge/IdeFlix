package org.epita.exposition.mediaDataBase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.epita.exposition.dto.common.TypeMedia;

import java.util.List;

public class MediaDataBaseResponseDto {

    // DTO en réponse vers IHM
    @Schema(name = "identifiant du media dans la movie Data Base", example = "8265", required = true)
    private long idDataBase;
    @Schema(name = "titre du media", example = "Bienvenue chez les Ch'tis", required = true)
    private String titre;
    @Schema(name = "date de sortie du media", example = "2008-02-20", required = true)
    private String dateSortie;

    private int duree;

    private String resume;

    private String cheminAffichePortrait;

    private String cheminAffichePaysage;

    private float noteDataBase;

    private List<GenreDataBaseResponseDto> genreDataBaseResponseDtos;

    private TypeMedia typeMedia;

    private int nombreSaisons;

    public MediaDataBaseResponseDto() {
    }

    public MediaDataBaseResponseDto(long idDataBase, String titre, String dateSortie, int duree, String resume, String cheminAffichePortrait, String cheminAffichePaysage, float noteDataBase, List<GenreDataBaseResponseDto> genreDataBaseResponseDtos, TypeMedia typeMedia, int nombreSaisons) {
        this.idDataBase = idDataBase;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.duree = duree;
        this.resume = resume;
        this.cheminAffichePortrait = cheminAffichePortrait;
        this.cheminAffichePaysage = cheminAffichePaysage;
        this.noteDataBase = noteDataBase;
        this.genreDataBaseResponseDtos = genreDataBaseResponseDtos;
        this.typeMedia = typeMedia;
        this.nombreSaisons = nombreSaisons;
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

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
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
        return cheminAffichePaysage;
    }

    public void setCheminAffichePaysage(String cheminAffichePaysage) {
        this.cheminAffichePaysage = cheminAffichePaysage;
    }

    public float getNoteDataBase() {
        return noteDataBase;
    }

    public void setNoteDataBase(float noteDataBase) {
        this.noteDataBase = noteDataBase;
    }

    public List<GenreDataBaseResponseDto> getGenreDataBaseResponseDtos() {
        return genreDataBaseResponseDtos;
    }

    public void setGenreDataBaseResponseDtos(List<GenreDataBaseResponseDto> genreDataBaseResponseDtos) {
        this.genreDataBaseResponseDtos = genreDataBaseResponseDtos;
    }

    public TypeMedia getTypeMedia() {
        return typeMedia;
    }

    public void setTypeMedia(TypeMedia typeMedia) {
        this.typeMedia = typeMedia;
    }

    public int getNombreSaisons() {
        return nombreSaisons;
    }

    public void setNombreSaisons(int nombreSaisons) {
        this.nombreSaisons = nombreSaisons;
    }

    @Override
    public String toString() {
        return "MediaDataBaseResponseDto{" +
                "idDataBase=" + idDataBase +
                ", titre='" + titre + '\'' +
                ", dateSortie='" + dateSortie + '\'' +
                ", duree=" + duree +
                ", resume='" + resume + '\'' +
                ", cheminAffichePortrait='" + cheminAffichePortrait + '\'' +
                ", cheminAffichePaysage='" + cheminAffichePaysage + '\'' +
                ", noteDataBase=" + noteDataBase +
                ", genreDataBaseResponseDtos=" + genreDataBaseResponseDtos +
                ", typeMedia=" + typeMedia +
                ", nombreSaisons=" + nombreSaisons +
                '}';
    }
}
