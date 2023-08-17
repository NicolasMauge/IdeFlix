package org.epita.domaine.mediaDataBase;

import java.time.LocalDate;
import java.util.List;

public class SerieDataBase extends MediaAudioVisuelDataBase{

    private int nombreSaisons;

    public SerieDataBase() {
    }

    public SerieDataBase(long idDataBase, String titre, List<GenreDataBase> genres, String resume, String cheminAffichePortrait, String cheminAffichePaysage, LocalDate dateSortie, int duree, float noteDataBase, int nombreSaisons) {
        super(idDataBase, titre, genres, resume, cheminAffichePortrait, cheminAffichePaysage, dateSortie, duree, noteDataBase);
        this.nombreSaisons = nombreSaisons;
    }


    public int getNombreSaisons() {
        return nombreSaisons;
    }

    public void setNombreSaisons(int nombreSaisons) {
        this.nombreSaisons = nombreSaisons;
    }

    @Override
    public String toString() {
        return "SerieDataBase{" +
                "nombreSaisons=" + nombreSaisons +
                '}';
    }
}
