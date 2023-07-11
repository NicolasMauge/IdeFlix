package org.epita.ideflixiam.application;

import org.epita.ideflixiam.domaine.UtilisateurIam;
import org.epita.ideflixiam.infrastructure.UtilisateurIamRepository;

import java.util.List;

public class UtilisateurIamServiceImpl implements UtilisateurIamService {

    UtilisateurIamRepository utilisateurIamRepository;

    public UtilisateurIamServiceImpl(UtilisateurIamRepository utilisateurIamRepository) {
        this.utilisateurIamRepository = utilisateurIamRepository;
    }

    @Override
    public UtilisateurIam creerUtilisateurIam(UtilisateurIam nouvelUtilisateurIam) {
        return utilisateurIamRepository.save(nouvelUtilisateurIam);
    }

    @Override
    public UtilisateurIam recupererUtilisateurIamParEmail(String email) {
        return utilisateurIamRepository.getUtilisateurIamByEmail(email);
    }

    @Override
    public List<UtilisateurIam> recupererAllUtilisateurIam() {

        return utilisateurIamRepository.findAll();
    }

    @Override
    public UtilisateurIam mettreAJourUtilisateurIam(UtilisateurIam utilisateurIam) {

        return utilisateurIamRepository.save(utilisateurIam);
    }

    @Override
    public void supprimerUtilisateurIam(UtilisateurIam utilisateurIam) {

        utilisateurIamRepository.delete(utilisateurIam);

    }
}
