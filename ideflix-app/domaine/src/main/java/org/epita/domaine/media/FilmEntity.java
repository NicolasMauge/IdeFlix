package org.epita.domaine.media;


import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class FilmEntity extends MediaAudioVisuelEntity {
    public FilmEntity() {
    }

    public FilmEntity(String idTmdb, String titre, LocalDate dateSortie, int duree, String cheminAffichePortrait, String cheminAffichePaysage, int noteTmdb, List<GenreEntity> genreEntityList) {
        super(idTmdb, titre, dateSortie, duree, cheminAffichePortrait, cheminAffichePaysage, noteTmdb, genreEntityList);
    }

    public FilmEntity(Long id, String idTmdb, String titre, LocalDate dateSortie, int duree, String cheminAffichePortrait, String cheminAffichePaysage, int noteTmdb, List<GenreEntity> genreEntityList) {
        super(id, idTmdb, titre, dateSortie, duree, cheminAffichePortrait, cheminAffichePaysage, noteTmdb, genreEntityList);
    }
}
