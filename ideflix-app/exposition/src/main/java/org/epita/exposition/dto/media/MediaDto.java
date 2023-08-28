package org.epita.exposition.dto.media;

import io.swagger.v3.oas.annotations.media.Schema;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.media.GenreDto;

import java.time.LocalDate;
import java.util.List;

public class MediaDto {
    @Schema(description = "Identifiant du fournisseur de données (The Movie Database).")
    private String idTmdb;
    @Schema(description = "Type de média (série ou film).")
    private TypeMedia typeMedia;
    @Schema(description = "Titre du média")
    private String titre;
    @Schema(description = "Date de sortie")
    private LocalDate dateSortie;

    @Schema(description = "Durée du média en minutes", example = "135")
    private int duree;
    @Schema(description = "Chemin de l'affiche dans TMDB au format portrait", example = "/pEoqbqtLc4CcwDUDqxmEDSWpWTZ.jpg")
    private String cheminAffichePortrait;
    @Schema(description = "Chemin de l'affiche dans TMDB au format paysage", example = "/oMsxZEvz9a708d49b6UdZK1KAo5.jpg")
    private String cheminAffichePaysage;
    @Schema(description = "Score TMDB", example = "8")
    private int noteTmdb;
    @Schema(description = "Liste des genres du média")
    private List<GenreDto> genreList;
    @Schema(description = "Pour les séries, nombre de saisons")
    private int nombreSaisons;

    public MediaDto() {
    }

    public MediaDto(String idTmdb, TypeMedia typeMedia, String titre, LocalDate dateSortie, int duree, String cheminAffichePortrait, String cheminAffichePaysage, int noteTmdb, List<GenreDto> genreList, int nombreSaisons) {
        this.idTmdb = idTmdb;
        this.typeMedia = typeMedia;
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

    public TypeMedia getTypeMedia() {
        return typeMedia;
    }

    public void setTypeMedia(TypeMedia typeMedia) {
        this.typeMedia = typeMedia;
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
