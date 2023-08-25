package org.epita.application.mediaDataBase.movieDataBase;

import org.epita.application.media.genre.GenreService;
import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.infrastructure.mediaDataBase.MovieDataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDataBaseServiceImpl implements MovieDataBaseService {

    private static final Logger logger = LoggerFactory.getLogger(MovieDataBaseServiceImpl.class);
    PreferencesUtilisateurService preferencesUtilisateurService;


    MovieDataBaseRepository movieDataBaseRepository;

    public MovieDataBaseServiceImpl(PreferencesUtilisateurService preferencesUtilisateurService, MovieDataBaseRepository movieDataBaseRepository) {
        this.preferencesUtilisateurService = preferencesUtilisateurService;
        this.movieDataBaseRepository = movieDataBaseRepository;
    }

    @Override
    public List<MovieDataBase> searchMovies(String query) {

        logger.debug("recherche liste films selon " + query);
        return movieDataBaseRepository.searchAllMovieDataBaseWithQuery(query);
    }

    @Override
    public MovieDataBase findMovieById(long Id) {
        logger.debug("recherche détail d'un films selon id" + Id);
        return movieDataBaseRepository.findDetailMovieDataBase(Id);
    }

    @Override
    public List<MovieDataBase> searchSuggestedMovies(int page) {
        logger.debug("recherche suggestion des films , page: " + page);
        return movieDataBaseRepository.searchSuggestedMovieDatabase(page);
    }

    @Override
    public List<MovieDataBase> searchSuggestedMoviesSelonPreferences(String email, int page) {

        PreferencesUtilisateurEntity preferencesUtilisateur;
        List<GenreEntity> listeGenresPreferes;

        logger.debug("recherche suggestion des films selon préférences utilisateur (" + email + "), page: " + page);
        List<MovieDataBase> movieDataBaseList = movieDataBaseRepository.searchSuggestedMovieDatabase(page);


        preferencesUtilisateur = preferencesUtilisateurService.trouverPreferenceUtilisateurParEmailUtilisateur(email);

        listeGenresPreferes = preferencesUtilisateur.getGenreList();
        List<Integer> listIdDatabaseGenresPreferes = listeGenresPreferes.stream().map(genre -> Integer.parseInt(genre.getIdTmdb())).toList();

        return movieDataBaseList.stream().filter(film -> {
                    // on va chercher si au moins un des genres du film fait partie des genres préférés.
                    List<Integer> listeIdDatabaseGenreDuFilm = film.getGenres().stream().map(GenreDataBase::getIdDatabase).toList();
                    // il y a un genre préféré si l'intersection n'est pas vide :
                    return !listeIdDatabaseGenreDuFilm.stream().filter(listIdDatabaseGenresPreferes::contains).toList().isEmpty();
                }
        ).toList();

    }
}
