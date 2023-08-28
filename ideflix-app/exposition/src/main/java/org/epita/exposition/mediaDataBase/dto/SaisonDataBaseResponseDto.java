package org.epita.exposition.mediaDataBase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SaisonDataBaseResponseDto {

    // DTO en réponse vers IHM

    @Schema(description = "Date de sortie de la série", example = "2008-02-20", required = true)
    private LocalDate dateSortie;

    @Schema(description = "Nom d'épisodes dans la saison")
    private int nombreEpisodes;
    @Schema(description = "Identifiant de la movie Data Base", example = "693", required = true)
    private long idDataBase;
    private String titre;
    @Schema(description = "Résumé de la saison")
    private String resume;
    @Schema(description = "Nom du fichier précédé d'un slash pour l'affiche au format portrait chez le fournisseur de données", example = "/pEoqbqtLc4CcwDUDqxmEDSWpWTZ.jpg")
    private String cheminAffichePortrait;

    @Schema(description = "Numéro de la saison.")
    private int numeroSaison;
    @Schema(description = "Score chez le fournisseur de données")
    private float noteDataBase;

    public SaisonDataBaseResponseDto() {
    }

    public SaisonDataBaseResponseDto(LocalDate dateSortie, int nombreEpisodes, long idDataBase, String titre, String resume, String cheminAffichePortrait, int numeroSaison, float noteDataBase) {
        this.dateSortie = dateSortie;
        this.nombreEpisodes = nombreEpisodes;
        this.idDataBase = idDataBase;
        this.titre = titre;
        this.resume = resume;
        this.cheminAffichePortrait = cheminAffichePortrait;
        this.numeroSaison = numeroSaison;
        this.noteDataBase = noteDataBase;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getNombreEpisodes() {
        return nombreEpisodes;
    }

    public void setNombreEpisodes(int nombreEpisodes) {
        this.nombreEpisodes = nombreEpisodes;
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
