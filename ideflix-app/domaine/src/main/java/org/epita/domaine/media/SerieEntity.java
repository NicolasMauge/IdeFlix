package org.epita.domaine.media;


import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class SerieEntity extends MediaAudioVisuelEntity {
    private int nombreSaisons;

    public SerieEntity() {
    }

    public SerieEntity(String idTmdb, String titre, LocalDate dateSortie, int duree, String cheminAffichePortrait, String cheminAffichePaysage, int noteTmdb, List<GenreEntity> genreEntityList, int nombreSaisons) {
        super(idTmdb, titre, dateSortie, duree, cheminAffichePortrait, cheminAffichePaysage, noteTmdb, genreEntityList);
        this.nombreSaisons = nombreSaisons;
    }

    public SerieEntity(Long id, String idTmdb, String titre, LocalDate dateSortie, int duree, String cheminAffichePortrait, String cheminAffichePaysage, int noteTmdb, List<GenreEntity> genreEntityList, int nombreSaisons) {
        super(id, idTmdb, titre, dateSortie, duree, cheminAffichePortrait, cheminAffichePaysage, noteTmdb, genreEntityList);
        this.nombreSaisons = nombreSaisons;
    }

    public int getNombreSaisons() {
        return nombreSaisons;
    }

    public void setNombreSaisons(int nombreSaisons) {
        this.nombreSaisons = nombreSaisons;
    }
}
