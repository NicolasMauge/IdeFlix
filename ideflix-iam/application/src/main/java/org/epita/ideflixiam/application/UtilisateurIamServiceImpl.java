package org.epita.ideflixiam.application;

import org.epita.ideflixiam.domaine.Role;
import org.epita.ideflixiam.domaine.UtilisateurIam;
import org.epita.ideflixiam.infrastructure.UtilisateurIamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.epita.ideflixiam.application.Util.ROLE_ADMIN;
import static org.epita.ideflixiam.application.Util.ROLE_UTILISATEUR_STANDARD;

@Service
public class UtilisateurIamServiceImpl implements UtilisateurIamService {

    UtilisateurIamRepository utilisateurIamRepository;

    public UtilisateurIamServiceImpl(UtilisateurIamRepository utilisateurIamRepository) {
        this.utilisateurIamRepository = utilisateurIamRepository;
    }

    @Override
    public UtilisateurIam creerUtilisateurIam(UtilisateurIam nouvelUtilisateurIam) {

        if (utilisateurIamRepository.getUtilisateurIamByEmail(nouvelUtilisateurIam.getEmail()) == null) {

            if (nouvelUtilisateurIam.getListeRoles().size() == 0) {
                List<Role> listRoles = new ArrayList<Role>();
                listRoles.add(new Role(ROLE_UTILISATEUR_STANDARD));

                nouvelUtilisateurIam.setListeRoles(listRoles);
            }

            return utilisateurIamRepository.save(nouvelUtilisateurIam);
        } else {
            return null; // TODO existe déjà
        }
    }

    @Override
    public UtilisateurIam recupererUtilisateurIamParEmail(String email) {
        return utilisateurIamRepository.getUtilisateurIamByEmail(email);
    }

    @Override
    public List<UtilisateurIam> recupererTousUtilisateurIam() {

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
