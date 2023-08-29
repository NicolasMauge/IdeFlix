package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.infrastructure.mediaDataBase.SerieDataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieDataBaseServiceImpl implements SerieDataBaseService {
    private static final Logger logger = LoggerFactory.getLogger(SerieDataBaseServiceImpl.class);


    SerieDataBaseRepository serieDataBaseRepository;

    PreferencesUtilisateurService preferencesUtilisateurService;

    public SerieDataBaseServiceImpl(SerieDataBaseRepository serieDataBaseRepository, PreferencesUtilisateurService preferencesUtilisateurService) {
        this.serieDataBaseRepository = serieDataBaseRepository;
        this.preferencesUtilisateurService = preferencesUtilisateurService;
    }

    @Override
    public List<SerieDataBase> searchSeries(String query) {
        logger.debug("recherche liste séries selon " + query);
        return serieDataBaseRepository.searchSeriesDataBase(query);
    }

    @Override
    public SerieDataBase findSerieById(long Id)  {
        logger.debug("recherche détail d'une série selon id" + Id);
            return serieDataBaseRepository.findDetailSerieDataBase(Id);
    }

    @Override
    public List<SerieDataBase> searchSuggestedSeries(int page) {
        logger.debug("recherche suggestion des séries , page: " + page);
        return serieDataBaseRepository.searchSuggestedSerieDataBase(page);
    }

    @Override
    public List<SerieDataBase> searchSuggestedSeriesSelonPreferences(String email, int page) {
        PreferencesUtilisateurEntity preferencesUtilisateur;
        List<GenreEntity> listeGenresPreferes;
        List<Integer> listIdDatabaseGenresPreferes = null;
        boolean preferencesExistent;

        logger.debug("recherche suggestion des séries selon préférences utilisateur (" + email + "), page: " + page);
        List<SerieDataBase> serieDataBaseList = serieDataBaseRepository.searchSuggestedSerieDataBase(page);

        try {
            preferencesUtilisateur = preferencesUtilisateurService.trouverPreferenceUtilisateurParEmailUtilisateur(email);

            listeGenresPreferes = preferencesUtilisateur.getGenreList();

            if (listeGenresPreferes.isEmpty()) {
                preferencesExistent = false;
            } else {
                listIdDatabaseGenresPreferes = listeGenresPreferes.stream().map(genre -> Integer.parseInt(genre.getIdTmdb())).toList();
                if (listIdDatabaseGenresPreferes.isEmpty()) {
                    preferencesExistent = false;
                } else {
                    preferencesExistent = true;
                }
            }

        } catch (EntityNotFoundException e) {
            preferencesExistent = false;
        }


        List<Integer> finalListIdDatabaseGenresPreferes = listIdDatabaseGenresPreferes;

        if (preferencesExistent == false)
            return serieDataBaseList;
        else {
            return serieDataBaseList.stream().filter(film -> {
                        // on va chercher si au moins un des genres de la série fait partie des genres préférés.
                        List<Integer> listeIdDatabaseGenreSerie = film.getGenres().stream().map(GenreDataBase::getIdDatabase).toList();
                        // il y a un genre préféré si l'intersection n'est pas vide :
                        if (finalListIdDatabaseGenresPreferes == null)
                            return false;
                        else
                            return !listeIdDatabaseGenreSerie.stream().filter(finalListIdDatabaseGenresPreferes::contains).toList().isEmpty();
                    }
            ).toList();
        }
    }
}
