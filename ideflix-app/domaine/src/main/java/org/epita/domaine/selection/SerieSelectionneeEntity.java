package org.epita.domaine.selection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.epita.domaine.media.MediaAudioVisuelEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class SerieSelectionneeEntity extends MediaSelectionneEntity {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateModification;
    private int numeroSaison;
    private String idTmdbSaison;
    private int numeroEpisode;
    private String idTmdbEpisode;

    public SerieSelectionneeEntity() {
    }

    public SerieSelectionneeEntity(Boolean avisPouce, LocalDate dateSelection, List<EtiquetteEntity> etiquetteEntityList, StatutMediaEntity statutMediaEntity, MediaAudioVisuelEntity mediaAudioVisuelEntity, UtilisateurEntity utilisateurEntity,
                                 LocalDate dateModification, int numeroSaison, String idTmdbSaison, int numeroEpisode, String idTmdbEpisode) {
        super(avisPouce, dateSelection, etiquetteEntityList, statutMediaEntity, mediaAudioVisuelEntity, utilisateurEntity);
        this.dateModification = dateModification;
        this.numeroSaison = numeroSaison;
        this.idTmdbSaison = idTmdbSaison;
        this.numeroEpisode = numeroEpisode;
        this.idTmdbEpisode = idTmdbEpisode;
    }

    public SerieSelectionneeEntity(Long id, Boolean avisPouce, LocalDate dateSelection, List<EtiquetteEntity> etiquetteEntityList, StatutMediaEntity statutMediaEntity, MediaAudioVisuelEntity mediaAudioVisuelEntity, UtilisateurEntity utilisateurEntity,
                                   LocalDate dateModification, int numeroSaison, String idTmdbSaison, int numeroEpisode, String idTmdbEpisode) {
        super(id, avisPouce, dateSelection, etiquetteEntityList, statutMediaEntity, mediaAudioVisuelEntity, utilisateurEntity);
        this.dateModification = dateModification;
        this.numeroSaison = numeroSaison;
        this.idTmdbSaison = idTmdbSaison;
        this.numeroEpisode = numeroEpisode;
        this.idTmdbEpisode = idTmdbEpisode;
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
