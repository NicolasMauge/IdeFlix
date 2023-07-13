package org.epita.exposition.selection.serieselectionnee;

import org.epita.domaine.selection.StatutMediaEntity;
import org.epita.exposition.selection.etiquette.EtiquetteDto;

import java.time.LocalDate;
import java.util.List;

public class SerieSelectionneeDto {
    private Boolean avisPouce;
    private LocalDate dateSelection;
    private List<EtiquetteDto> etiquetteList;
    private StatutMediaEntity statutMedia;
    private Long idFilm;
    private String idTmdb;
    private String titre;
    private LocalDate dateSortie;
    private int duree;
    private String cheminAffichePortrait;
    private String cheminAffichePaysage;
    private int noteTmdb;
    private Long idUtilisateur;
    private LocalDate dateModification;
    private int numeroSaison;
    private String idTmdbSaison;
    private int numeroEpisode;
    private String idTmdbEpisode;

    public SerieSelectionneeDto(Boolean avisPouce, LocalDate dateSelection, List<EtiquetteDto> etiquetteList, StatutMediaEntity statutMedia, Long idFilm, String idTmdb, String titre, LocalDate dateSortie, int duree, String cheminAffichePortrait, String cheminAffichePaysage, int noteTmdb, Long idUtilisateur, LocalDate dateModification, int numeroSaison, String idTmdbSaison, int numeroEpisode, String idTmdbEpisode) {
        this.avisPouce = avisPouce;
        this.dateSelection = dateSelection;
        this.etiquetteList = etiquetteList;
        this.statutMedia = statutMedia;
        this.idFilm = idFilm;
        this.idTmdb = idTmdb;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.duree = duree;
        this.cheminAffichePortrait = cheminAffichePortrait;
        this.cheminAffichePaysage = cheminAffichePaysage;
        this.noteTmdb = noteTmdb;
        this.idUtilisateur = idUtilisateur;
        this.dateModification = dateModification;
        this.numeroSaison = numeroSaison;
        this.idTmdbSaison = idTmdbSaison;
        this.numeroEpisode = numeroEpisode;
        this.idTmdbEpisode = idTmdbEpisode;
    }

    public Boolean getAvisPouce() {
        return avisPouce;
    }

    public void setAvisPouce(Boolean avisPouce) {
        this.avisPouce = avisPouce;
    }

    public LocalDate getDateSelection() {
        return dateSelection;
    }

    public void setDateSelection(LocalDate dateSelection) {
        this.dateSelection = dateSelection;
    }

    public List<EtiquetteDto> getEtiquetteList() {
        return etiquetteList;
    }

    public void setEtiquetteList(List<EtiquetteDto> etiquetteList) {
        this.etiquetteList = etiquetteList;
    }

    public StatutMediaEntity getStatutMedia() {
        return statutMedia;
    }

    public void setStatutMedia(StatutMediaEntity statutMedia) {
        this.statutMedia = statutMedia;
    }

    public Long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(Long idFilm) {
        this.idFilm = idFilm;
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

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public LocalDate getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public int getNumeroSaison() {
        return numeroSaison;
    }

    public void setNumeroSaison(int numeroSaison) {
        this.numeroSaison = numeroSaison;
    }

    public String getIdTmdbSaison() {
        return idTmdbSaison;
    }

    public void setIdTmdbSaison(String idTmdbSaison) {
        this.idTmdbSaison = idTmdbSaison;
    }

    public int getNumeroEpisode() {
        return numeroEpisode;
    }

    public void setNumeroEpisode(int numeroEpisode) {
        this.numeroEpisode = numeroEpisode;
    }

    public String getIdTmdbEpisode() {
        return idTmdbEpisode;
    }

    public void setIdTmdbEpisode(String idTmdbEpisode) {
        this.idTmdbEpisode = idTmdbEpisode;
    }
}
