package org.epita.ideflixiam.application.utilisateur;

import org.epita.ideflixiam.application.common.UtileRole;
import org.epita.ideflixiam.application.exception.UtilisateurExistantDejaException;
import org.epita.ideflixiam.application.exception.UtilisateurInexistantException;
import org.epita.ideflixiam.domaine.RoleEntity;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.epita.ideflixiam.infrastructure.RoleRepository;
import org.epita.ideflixiam.infrastructure.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.epita.ideflixiam.application.common.UtileRole.ROLE_UTILISATEUR;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final static Logger logger = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        if (this.passwordEncoder == null) {
            this.passwordEncoder = new BCryptPasswordEncoder();
        }
        return this.passwordEncoder;
    }

    @Override
    public UtilisateurEntity creerUtilisateur(final UtilisateurEntity nouvelUtilisateurEntity) throws UtilisateurExistantDejaException {

        Optional<UtilisateurEntity> utilisateurEntityOptional = utilisateurRepository.findByEmail(nouvelUtilisateurEntity.getEmail());

        if (utilisateurEntityOptional.isEmpty()) {

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

            // TODO appeler IdeFlix-app ?

            return utilisateurRepository.save(nouvelUtilisateurEntity);
        } else {
            throw new UtilisateurExistantDejaException("L'utilisateur " + nouvelUtilisateurEntity.getEmail() + " existe déjà");
            // Pour des raisons de sécurité, on pourrait ignorer la demande de recréation en renvoyant les données
            // attendues/imaginée par un éventuel fraudeur.
            // Le mot de passe de l'utilisateur ne serait donc pas changé :
            //return nouvelUtilisateurEntity ;
        }
    }

    @Override
    public UtilisateurEntity recupererUtilisateurParEmail(final String email) throws UtilisateurInexistantException {
        return utilisateurRepository
                .findByEmail(email)
                .orElseThrow(() -> new UtilisateurInexistantException("Utilisateur ou mot de passe erroné"));
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

        if (utilisateurRepository.findByEmail(emailAdmin).isEmpty()) {
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
