package org.epita.ideflixiam.application.utilisateur;

import org.epita.ideflixiam.domaine.UtilisateurEntity;

import java.util.List;

public interface UtilisateurService {

    UtilisateurEntity creerUtilisateur(UtilisateurEntity nouvelUtilisateurEntity);

    UtilisateurEntity recupererUtilisateurParEmail(String email);


    List<UtilisateurEntity> recupererUtilisateurs();


    UtilisateurEntity mettreAJourUtilisateur(UtilisateurEntity utilisateurEntity);


    void supprimerUtilisateur(UtilisateurEntity utilisateurEntity);

    void creerPremierAdministrateur(String nomAdmin, String prenomAdmin, String emailAdmin, String motDePasseAdmin);


}
