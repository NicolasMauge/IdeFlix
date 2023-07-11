package org.epita.ideflixiam.application;

import org.epita.ideflixiam.domaine.UtilisateurIam;

import java.util.List;

public interface UtilisateurIamService {

    UtilisateurIam creerUtilisateurIam(UtilisateurIam nouvelUtilisateurIam);

    UtilisateurIam recupererUtilisateurIamParEmail(String email);


    List<UtilisateurIam> recupererTousUtilisateurIam();


    UtilisateurIam mettreAJourUtilisateurIam(UtilisateurIam utilisateurIam);


    void supprimerUtilisateurIam(UtilisateurIam utilisateurIam);
}
