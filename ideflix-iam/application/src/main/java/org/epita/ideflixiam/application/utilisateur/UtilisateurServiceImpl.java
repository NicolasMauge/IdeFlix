package org.epita.ideflixiam.application.utilisateur;

import org.epita.ideflixiam.application.common.Util;
import org.epita.ideflixiam.domaine.RoleEntity;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.epita.ideflixiam.infrastructure.RoleRepository;
import org.epita.ideflixiam.infrastructure.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.epita.ideflixiam.application.common.Util.ROLE_UTILISATEUR;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final static Logger logger = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    UtilisateurRepository utilisateurRepository;
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UtilisateurEntity creerUtilisateur(UtilisateurEntity nouvelUtilisateurEntity) {

        if (utilisateurRepository.findByEmail(nouvelUtilisateurEntity.getEmail()) == null) {

            if (nouvelUtilisateurEntity.getListeRoles().size() == 0) {
                List<RoleEntity> listRoleEntities = new ArrayList<RoleEntity>();
                listRoleEntities.add(new RoleEntity(ROLE_UTILISATEUR));

                nouvelUtilisateurEntity.setListeRoles(listRoleEntities);
            }

            String motDePasseChiffre = passwordEncoder.encode(nouvelUtilisateurEntity.getMotDePasse());
            nouvelUtilisateurEntity.setMotDePasse(motDePasseChiffre); // on met le mot de passe haché avant sauvegarde


            logger.debug("Création de l'utilisateur IAM " + nouvelUtilisateurEntity.getEmail());

            // TODO appeler ideflix-app

            return utilisateurRepository.save(nouvelUtilisateurEntity);
        } else {
            logger.debug("L'utilisateur " + nouvelUtilisateurEntity.getEmail() + " existe déjà");

            return null; // TODO existe déjà (erreur 400 ?)
        }
    }

    @Override
    public UtilisateurEntity recupererUtilisateurParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public List<UtilisateurEntity> recupererUtilisateurs() {

        return utilisateurRepository.findAll();
    }

    @Override
    public UtilisateurEntity mettreAJourUtilisateur(UtilisateurEntity utilisateurEntity) {

        return utilisateurRepository.save(utilisateurEntity);
    }

    @Override
    public void supprimerUtilisateur(UtilisateurEntity utilisateurEntity) {

        utilisateurRepository.delete(utilisateurEntity);

    }

    @Override
    public void creerPremierAdministrateur(String nomAdmin, String prenomAdmin, String emailAdmin, String motDePasseAdmin) {
        List<RoleEntity> listeRole = new ArrayList<>();

        RoleEntity roleAdmin = roleRepository.findRoleByNomRole(Util.ROLE_ADMIN);
        if (roleAdmin == null)
            roleAdmin = new RoleEntity(Util.ROLE_ADMIN);

        RoleEntity roleUtilisateur = roleRepository.findRoleByNomRole(ROLE_UTILISATEUR);
        if (roleUtilisateur == null)
            roleUtilisateur = new RoleEntity(ROLE_UTILISATEUR);

        listeRole.add(roleAdmin);
        listeRole.add(roleUtilisateur);

        UtilisateurEntity utilisateur = new UtilisateurEntity(nomAdmin, prenomAdmin, emailAdmin, motDePasseAdmin, listeRole);

        creerUtilisateur(utilisateur);
    }
}
