package org.epita.domaine.mediaDataBase;

import java.time.LocalDate;
import java.util.List;

public class SerieDataBase extends MediaAudioVisuelDataBase{

    private int nombreSaisons;

    private List<SaisonSerieDataBase> listeSaisons;

    public SerieDataBase() {
    }

    public SerieDataBase(long idDataBase, String titre, List<GenreDataBase> genres, String resume, String cheminAffichePortrait, String cheminAffichePaysage, LocalDate dateSortie, int duree, float noteDataBase, int nombreSaisons, List<SaisonSerieDataBase> listeSaisons) {
        super(idDataBase, titre, genres, resume, cheminAffichePortrait, cheminAffichePaysage, dateSortie, duree, noteDataBase);
        this.nombreSaisons = nombreSaisons;
        this.listeSaisons = listeSaisons;
    }

    public int getNombreSaisons() {
        return nombreSaisons;
    }

    public void setNombreSaisons(int nombreSaisons) {
        this.nombreSaisons = nombreSaisons;
    }

    public List<SaisonSerieDataBase> getListeSaisons() {
        return listeSaisons;
    }

    public void setListeSaisons(List<SaisonSerieDataBase> listeSaisons) {
        this.listeSaisons = listeSaisons;
    }

    @Override
    public String toString() {
        return "SerieDataBase{" +
                "nombreSaisons=" + nombreSaisons +
                ", listeSaisons=" + listeSaisons +
                '}';
    }
}
