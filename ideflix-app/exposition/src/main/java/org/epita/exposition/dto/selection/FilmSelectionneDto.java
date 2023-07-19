package org.epita.exposition.dto.selection;

import org.epita.domaine.selection.StatutMediaEntity;

import java.time.LocalDate;
import java.util.List;

public class FilmSelectionneDto {
    private Long id;
    private Boolean avisPouce;
    private LocalDate dateSelection;
    private List<EtiquetteDto> etiquetteList;
    private StatutMediaEntity statutMedia;
    private String idTmdb;
    private Long idUtilisateur;

    public FilmSelectionneDto() {
    }

    public FilmSelectionneDto(Long id, Boolean avisPouce, LocalDate dateSelection, List<EtiquetteDto> etiquetteList, StatutMediaEntity statutMedia, String idTmdb, Long idUtilisateur) {
        this.id = id;
        this.avisPouce = avisPouce;
        this.dateSelection = dateSelection;
        this.etiquetteList = etiquetteList;
        this.statutMedia = statutMedia;
        this.idTmdb = idTmdb;
        this.idUtilisateur = idUtilisateur;
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

    public String getIdTmdb() {
        return idTmdb;
    }

    public void setIdTmdb(String idTmdb) {
        this.idTmdb = idTmdb;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
