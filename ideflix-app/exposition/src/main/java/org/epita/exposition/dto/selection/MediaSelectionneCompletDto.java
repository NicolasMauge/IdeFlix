package org.epita.exposition.dto.selection;

import org.epita.domaine.selection.StatutMediaEntity;
import org.epita.exposition.dto.media.MediaDto;

import java.time.LocalDate;
import java.util.List;

public class MediaSelectionneCompletDto {
    private Long id;
    private Boolean avisPouce;
    private LocalDate dateSelection;
    private List<EtiquetteDto> etiquetteList;
    private StatutMediaEntity statutMedia;
    private MediaDto media;
    private String email;
    private LocalDate dateModification;
    private int numeroSaison;
    private String idTmdbSaison;
    private int numeroEpisode;
    private String idTmdbEpisode;

    public MediaSelectionneCompletDto() {
    }

    public MediaSelectionneCompletDto(Long id, Boolean avisPouce, LocalDate dateSelection, List<EtiquetteDto> etiquetteList, StatutMediaEntity statutMedia, MediaDto media, String email, LocalDate dateModification, int numeroSaison, String idTmdbSaison, int numeroEpisode, String idTmdbEpisode) {
        this.id = id;
        this.avisPouce = avisPouce;
        this.dateSelection = dateSelection;
        this.etiquetteList = etiquetteList;
        this.statutMedia = statutMedia;
        this.media = media;
        this.email = email;
        this.dateModification = dateModification;
        this.numeroSaison = numeroSaison;
        this.idTmdbSaison = idTmdbSaison;
        this.numeroEpisode = numeroEpisode;
        this.idTmdbEpisode = idTmdbEpisode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MediaDto getMedia() {
        return media;
    }

    public void setMedia(MediaDto media) {
        this.media = media;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
