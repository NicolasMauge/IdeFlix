package org.epita.domaine.selection;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class SerieSelectionne extends MediaSelectionne {
    private LocalDate dateModification;
    private int numeroSaison;
    private String idTmdbSaison;
    private int numeroEpisode;
    private String getIdTmdbEpisode;

    public SerieSelectionne() {
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

    public String getGetIdTmdbEpisode() {
        return getIdTmdbEpisode;
    }

    public void setGetIdTmdbEpisode(String getIdTmdbEpisode) {
        this.getIdTmdbEpisode = getIdTmdbEpisode;
    }
}
