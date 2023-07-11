package org.epita.domaine.media;


import javax.persistence.Entity;

@Entity
public class SerieEntity extends MediaAudioVisuelEntity {
    private int nombreSaisons;

    public SerieEntity() {
    }

    public int getNombreSaisons() {
        return nombreSaisons;
    }

    public void setNombreSaisons(int nombreSaisons) {
        this.nombreSaisons = nombreSaisons;
    }
}
