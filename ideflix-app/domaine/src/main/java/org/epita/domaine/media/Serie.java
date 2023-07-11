package org.epita.domaine.media;


import javax.persistence.Entity;

@Entity
public class Serie extends MediaAudioVisuel {
    private int nombreSaisons;

    public Serie() {
    }

    public int getNombreSaisons() {
        return nombreSaisons;
    }

    public void setNombreSaisons(int nombreSaisons) {
        this.nombreSaisons = nombreSaisons;
    }
}
