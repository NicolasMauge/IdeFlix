package org.epita.application.iam.service;

import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.infrastructure.utilisateur.iam.UtilisateurIamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UtilisateurIamServiceImpl implements UtilisateurIamService {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurIamServiceImpl.class);

    UtilisateurIamRepository utilisateurIamRepository;
    UtilisateurService utilisateurService;
    PreferencesUtilisateurService preferencesUtilisateurService;

    FilmSelectionneService filmSelectionneService;
    SerieSelectionneeService serieSelectionneeService;

    public UtilisateurIamServiceImpl(UtilisateurIamRepository utilisateurIamRepository,
                                     UtilisateurService utilisateurService,
                                     PreferencesUtilisateurService preferencesUtilisateurService,
                                     FilmSelectionneService filmSelectionneService,
                                     SerieSelectionneeService serieSelectionneeService) {
        this.utilisateurIamRepository = utilisateurIamRepository;
        this.utilisateurService = utilisateurService;
        this.preferencesUtilisateurService = preferencesUtilisateurService;
        this.filmSelectionneService = filmSelectionneService;
        this.serieSelectionneeService = serieSelectionneeService;
    }

    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity utilisateurIamEntity) {

        // Création dans l'IAM :
        UtilisateurIamEntity nouvelUtilisateurIam = utilisateurIamRepository.creerUtilisateurIam(utilisateurIamEntity);

        logger.debug("IdeFlix - Utilisateur : " + nouvelUtilisateurIam.getEmail() + " créé dans l'IAM.");

        // Création dans l'APP :
        UtilisateurEntity utilisateur = new UtilisateurEntity(
                nouvelUtilisateurIam.getEmail(),
                nouvelUtilisateurIam.getNom(),
                nouvelUtilisateurIam.getPrenom(),
                nouvelUtilisateurIam.getDateCreation().toLocalDate()
        );

        utilisateurService.creerUtilisateur(utilisateur);
        // TODO : gérer le cas de l'échec de création de l'utilisateur dans l'APP
        //  ==> il faudra effacer l'utilisateur dans l'IAM puis déclencher un exception
        logger.debug("IdeFlix - Création utilisateur " + utilisateur.getEmail() + " dans l'APP (id=" + utilisateur.getId() + ").");

        return nouvelUtilisateurIam;
    }

    @Override
    public UtilisateurIamEntity loginIam(UtilisateurIamEntity utilisateurIamEntity) {
        logger.debug("IdeFlix - login utilisateur : " + utilisateurIamEntity.getEmail() + ".");
        return utilisateurIamRepository.loginIam(utilisateurIamEntity);
    }


    // pour les administrateurs :
    @Override
    public List<UtilisateurIamEntity> getUtilisateursIam(String headerAuthorization) {

        List<UtilisateurIamEntity> utilisateurIamEntityList;

        logger.debug("IdeFlix - Récupération de tous les utilisateurs.");
        utilisateurIamEntityList = utilisateurIamRepository.getUtilisateursIam(headerAuthorization);

        return utilisateurIamEntityList;
    }

    // pour les administrateurs :
    @Override
    @Transactional
    public void delUtilisateurIam(String headerAuthorization, String email) {
        logger.warn("IdeFlix - Effacement de l'utilisateur " + email + ".");

        // 1 - Effacement dans l'APP
        // 1.1 - Effacement des préférences :
        PreferencesUtilisateurEntity preferencesUtilisateur = null;
        Boolean utilisateurPossedeDesPreferences;

        try {
            preferencesUtilisateur = preferencesUtilisateurService.trouverPreferenceUtilisateurParEmailUtilisateur(email);
            if (preferencesUtilisateur != null) {
                utilisateurPossedeDesPreferences = true;
            } else {
                utilisateurPossedeDesPreferences = false;
            }
        } catch (EntityNotFoundException e) {
            utilisateurPossedeDesPreferences = false;
        }

        if (utilisateurPossedeDesPreferences) {
            logger.trace("IdeFlix - APP - Effacement des préférences de l'utilisateur " + email + "...");
            preferencesUtilisateurService.supprimerPreferencesUtilisateurParId(preferencesUtilisateur.getId());
            //logger.debug("IdeFlix - APP - Préférences de l'utilisateur " + email + " effacées.");
        } else {
            //logger.debug("IdeFlix - APP - L'utilisateur " + email + " n'avait pas de préférences.");
        }

        // 1.2 - Effacement des films sélectionnés
        logger.trace("IdeFlix - APP - Effacement des films sélectionnés par " + email + "...");
        filmSelectionneService.supprimerFilmsSelectionnesParEmail(email);

        // 1.3 - Effacement des séries sélectionnées
        logger.trace("IdeFlix - APP - Effacement des films sélectionnés par " + email + "...");
        serieSelectionneeService.supprimerSeriesSelectionneesParEmail(email);

        // 1.4 - Effacement de l'utilisateur APP :
        logger.trace("IdeFlix - APP - Effacement de l'utilisateur " + email + "...");
        utilisateurService.supprimerUtilisateurParEmail(email);

        // 2 - Effacement dans l'IAM
        logger.trace("IdeFlix - IAM - Effacement de l'utilisateur " + email + "...");
        utilisateurIamRepository.delUtilisateurIam(headerAuthorization, email);
        logger.warn("IdeFlix - IAM - Utilisateur " + email + " effacé.");


    }
}
