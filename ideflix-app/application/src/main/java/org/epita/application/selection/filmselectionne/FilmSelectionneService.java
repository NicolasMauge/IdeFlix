package org.epita.application.selection.filmselectionne;

import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;

import java.util.List;

public interface FilmSelectionneService {
    void creerFilmSelectionne(FilmSelectionneEntity filmSelectionneEntity);

    FilmSelectionneEntity trouverFilmSelectionneParId(Long id);

    List<FilmSelectionneEntity> trouverTousLesFilmsSelectionnes();

    void supprimerFilmSelectionneParId(Long id);

    List<FilmSelectionneEntity> trouverFilmSelectionneeParUtilisateur(UtilisateurEntity utilisateurEntity);

    List<FilmSelectionneEntity> trouverFilmsSelectionnesParEmailUtilisateur(String email);

    List<FilmSelectionneEntity> trouverFilmSelectionnesParEmailUtilisateurEtIdTmdb(String email, String idTmdb);

    void supprimerFilmsSelectionnesParEmail(String email);
}
