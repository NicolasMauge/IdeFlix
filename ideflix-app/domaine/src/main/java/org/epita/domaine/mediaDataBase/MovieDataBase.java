package org.epita.domaine.mediaDataBase;


import java.time.LocalDate;
import java.util.List;

public class MovieDataBase extends  MediaAudioVisuelDataBase{

    public MovieDataBase() {
    }

    public MovieDataBase(long idDataBase, String titre, List<GenreDataBase> genres, String resume, String cheminAffichePortrait, String cheminAffichePaysage, LocalDate dateSortie, int duree, float noteDataBase) {
        super(idDataBase, titre, genres, resume, cheminAffichePortrait, cheminAffichePaysage, dateSortie, duree, noteDataBase);
    }


}
