package org.epita.ideflixiam.application.utilisateur;

import org.epita.ideflixiam.application.common.UtilisateurExistantDejaException;
import org.epita.ideflixiam.application.common.UtilisateurInexistantException;
import org.epita.ideflixiam.domaine.UtilisateurEntity;

import java.util.List;

public interface UtilisateurService {

    UtilisateurEntity creerUtilisateur(UtilisateurEntity nouvelUtilisateurEntity) throws UtilisateurExistantDejaException;

    UtilisateurEntity recupererUtilisateurParEmail(String email) throws UtilisateurInexistantException;


    List<UtilisateurEntity> recupererUtilisateurs();


    UtilisateurEntity mettreAJourUtilisateur(UtilisateurEntity utilisateurEntity);


    void supprimerUtilisateur(UtilisateurEntity utilisateurEntity);

    void verifieQueIamEstInitialisee(String nomAdmin, String prenomAdmin, String emailAdmin, String motDePasseAdmin);


}
