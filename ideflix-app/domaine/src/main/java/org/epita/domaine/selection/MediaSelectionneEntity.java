package org.epita.domaine.selection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.epita.domaine.media.MediaAudioVisuelEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MediaSelectionneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean avisPouce;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateSelection;

    @ManyToMany
    private List<EtiquetteEntity> etiquetteEntityList;

    private StatutMediaEntity statutMediaEntity;

    @OneToOne
    private MediaAudioVisuelEntity mediaAudioVisuelEntity;

    @ManyToOne
    private UtilisateurEntity utilisateurEntity;

    public MediaSelectionneEntity() {
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

    public List<EtiquetteEntity> getEtiquetteEntityList() {
        return etiquetteEntityList;
    }

    public void setEtiquetteEntityList(List<EtiquetteEntity> etiquetteEntityList) {
        this.etiquetteEntityList = etiquetteEntityList;
    }

    public StatutMediaEntity getStatutMediaEntity() {
        return statutMediaEntity;
    }

    public void setStatutMediaEntity(StatutMediaEntity statutMediaEntity) {
        this.statutMediaEntity = statutMediaEntity;
    }

    public MediaAudioVisuelEntity getMediaAudioVisuelEntity() {
        return mediaAudioVisuelEntity;
    }

    public void setMediaAudioVisuelEntity(MediaAudioVisuelEntity mediaAudioVisuelEntity) {
        this.mediaAudioVisuelEntity = mediaAudioVisuelEntity;
    }

    public UtilisateurEntity getUtilisateurEntity() {
        return utilisateurEntity;
    }

    public void setUtilisateurEntity(UtilisateurEntity utilisateurEntity) {
        this.utilisateurEntity = utilisateurEntity;
    }
}
