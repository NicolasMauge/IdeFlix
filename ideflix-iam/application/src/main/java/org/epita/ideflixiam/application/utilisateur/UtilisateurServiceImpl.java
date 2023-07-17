package org.epita.ideflixiam.application.utilisateur;

import org.epita.ideflixiam.application.common.UtileRole;
import org.epita.ideflixiam.application.common.UtilisateurExistantDejaException;
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

import static org.epita.ideflixiam.application.common.UtileRole.ROLE_UTILISATEUR;

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
                logger.debug("IAM - Préparation de l'utilisateur standard " + nouvelUtilisateurEntity.getEmail());
                List<RoleEntity> listRoleEntities = new ArrayList<>();
                RoleEntity roleUtilisateur = roleRepository.findRoleByNomRole(ROLE_UTILISATEUR);

                listRoleEntities.add(roleUtilisateur);

                nouvelUtilisateurEntity.setListeRoles(listRoleEntities);
            }

            String motDePasseChiffre = passwordEncoder.encode(nouvelUtilisateurEntity.getMotDePasse());
            nouvelUtilisateurEntity.setMotDePasse(motDePasseChiffre); // on met le mot de passe haché avant sauvegarde


            logger.debug("IAM - Création de l'utilisateur " + nouvelUtilisateurEntity.getEmail());

            // TODO appeler ideflix-app ?

            return utilisateurRepository.save(nouvelUtilisateurEntity);
        } else {
            logger.debug("L'utilisateur " + nouvelUtilisateurEntity.getEmail() + " existe déjà");
            throw new UtilisateurExistantDejaException("L'utilisateur " + nouvelUtilisateurEntity.getEmail() + " existe déjà");

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
    public void verifieQueIamEstInitialisee(String nomAdmin, String prenomAdmin, String emailAdmin, String motDePasseAdmin) {

        if (utilisateurRepository.findByEmail(emailAdmin) == null) {
            // Si le premier administrateur n'existe pas, on le crée ainsi que les rôles.
            // (si le premier admin existe, c'est que les rôles sont déjà créés)

            List<RoleEntity> listeRole = new ArrayList<>();

            logger.debug("IAM - creation du premier administrateur");

            RoleEntity roleAdmin = roleRepository.findRoleByNomRole(UtileRole.ROLE_ADMIN);
            if (roleAdmin == null) {
                logger.debug("IAM - Le role admin n'existe pas --> création");
                roleAdmin = new RoleEntity(UtileRole.ROLE_ADMIN);
                roleRepository.save(roleAdmin);
            }

            RoleEntity roleUtilisateur = roleRepository.findRoleByNomRole(ROLE_UTILISATEUR);
            if (roleUtilisateur == null) {
                logger.debug("IAM - le role utilisateur n'existe pas --> création");
                roleUtilisateur = new RoleEntity(ROLE_UTILISATEUR);
                roleRepository.save(roleUtilisateur);
            }

            listeRole.add(roleAdmin);
            listeRole.add(roleUtilisateur);

            UtilisateurEntity utilisateur = new UtilisateurEntity(nomAdmin, prenomAdmin, emailAdmin, motDePasseAdmin, listeRole);

            creerUtilisateur(utilisateur);
        }
    }
}
