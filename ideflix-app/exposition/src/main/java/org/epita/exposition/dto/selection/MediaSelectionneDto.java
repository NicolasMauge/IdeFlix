package org.epita.exposition.dto.selection;

import io.swagger.v3.oas.annotations.media.Schema;
import org.epita.domaine.selection.StatutMediaEntity;
import org.epita.exposition.dto.common.TypeMedia;

import java.time.LocalDate;
import java.util.List;

public class MediaSelectionneDto {
    @Schema(description = "Type de média (série ou film).")
    private TypeMedia typeMedia;
    @Schema(description = "Réservé pour usage futur")
    private Boolean avisPouce;
    @Schema(description = "Date d'ajout dans la liste de l'utilisateur")
    private LocalDate dateSelection;
    @Schema(description = "Liste des étiquettes mises par l'utilisateur sur le média")
    private List<EtiquetteDto> etiquetteList;
    @Schema(description = "Statut du média choisi par l'utilisateur")
    private StatutMediaEntity statutMedia;
    @Schema(description = "Identifiant du média chez le fournisseur de données.")
    private String mediaIdTmdb;
    @Schema(description = "email de l'utilisateur")
    private String email;
    @Schema(description = "Date de modification de cette sélection par l'utilisateur.")
    private LocalDate dateModification;
    @Schema(description = "Numéro de la saison (série).")
    private int numeroSaison;
    @Schema(description = "Identifiant de la saison (série) chez le fournisseur de données.")
    private String idTmdbSaison;
    @Schema(description = "Numéro de l'épisode (série).")
    private int numeroEpisode;
    @Schema(description = "Identifiant de l'épisode' (série) chez le fournisseur de données.")
    private String idTmdbEpisode;

    public MediaSelectionneDto(TypeMedia typeMedia, Boolean avisPouce, LocalDate dateSelection, List<EtiquetteDto> etiquetteList, StatutMediaEntity statutMedia, String mediaIdTmdb, String email, LocalDate dateModification, int numeroSaison, String idTmdbSaison, int numeroEpisode, String idTmdbEpisode) {
        this.typeMedia = typeMedia;
        this.avisPouce = avisPouce;
        this.dateSelection = dateSelection;
        this.etiquetteList = etiquetteList;
        this.statutMedia = statutMedia;
        this.mediaIdTmdb = mediaIdTmdb;
        this.email = email;
        this.dateModification = dateModification;
        this.numeroSaison = numeroSaison;
        this.idTmdbSaison = idTmdbSaison;
        this.numeroEpisode = numeroEpisode;
        this.idTmdbEpisode = idTmdbEpisode;
    }

    public MediaSelectionneDto() {
    }

    public TypeMedia getTypeMedia() {
        return typeMedia;
    }

    public void setTypeMedia(TypeMedia typeMedia) {
        this.typeMedia = typeMedia;
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

    public String getMediaIdTmdb() {
        return mediaIdTmdb;
    }

    public void setMediaIdTmdb(String mediaIdTmdb) {
        this.mediaIdTmdb = mediaIdTmdb;
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
