package org.epita.domaine.selection;

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

    private LocalDate dateSelection;

    @OneToMany
    private List<EtiquetteEntity> etiquetteEntityList;

    private StatutMediaEntity statutMediaEntity;

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

    public List<EtiquetteEntity> getEtiquetteList() {
        return etiquetteEntityList;
    }

    public void setEtiquetteList(List<EtiquetteEntity> etiquetteEntityList) {
        this.etiquetteEntityList = etiquetteEntityList;
    }

    public StatutMediaEntity getStatutMedia() {
        return statutMediaEntity;
    }

    public void setStatutMedia(StatutMediaEntity statutMediaEntity) {
        this.statutMediaEntity = statutMediaEntity;
    }
}
