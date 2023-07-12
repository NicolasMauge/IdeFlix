package org.epita.ideflixiam.application.utilisateuriam;

import org.epita.ideflixiam.domaine.RoleEntity;
import org.epita.ideflixiam.domaine.UtilisateurIamEntity;
import org.epita.ideflixiam.infrastructure.UtilisateurIamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.epita.ideflixiam.application.common.Util.ROLE_UTILISATEUR_STANDARD;

@Service
public class UtilisateurIamServiceImpl implements UtilisateurIamService {

    private final static Logger logger = LoggerFactory.getLogger(UtilisateurIamServiceImpl.class);

    UtilisateurIamRepository utilisateurIamRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UtilisateurIamServiceImpl(UtilisateurIamRepository utilisateurIamRepository) {
        this.utilisateurIamRepository = utilisateurIamRepository;
    }

    @Override
    public UtilisateurIamEntity creerUtilisateurIam(UtilisateurIamEntity nouvelUtilisateurIamEntity) {

        if (utilisateurIamRepository.findByEmail(nouvelUtilisateurIamEntity.getEmail()) == null) {

            if (nouvelUtilisateurIamEntity.getListeRoles().size() == 0) {
                List<RoleEntity> listRoleEntities = new ArrayList<RoleEntity>();
                listRoleEntities.add(new RoleEntity(ROLE_UTILISATEUR_STANDARD));

                String motDePasseChiffre = passwordEncoder.encode(nouvelUtilisateurIamEntity.getMotDePasse());
                nouvelUtilisateurIamEntity.setMotDePasse(motDePasseChiffre); // on met le mot de passe haché avant sauvegarde

                nouvelUtilisateurIamEntity.setListeRoles(listRoleEntities);
            }

            logger.debug("Création de l'utilisateur IAM " + nouvelUtilisateurIamEntity.getEmail());

            // TODO appeler ideflix-app

            return utilisateurIamRepository.save(nouvelUtilisateurIamEntity);
        } else {
            logger.debug("L'utilisateur " + nouvelUtilisateurIamEntity.getEmail() + " existe déjà");

            return null; // TODO existe déjà (erreur 400 ?)
        }
    }

    @Override
    public UtilisateurIamEntity recupererUtilisateurIamParEmail(String email) {
        return utilisateurIamRepository.findByEmail(email);
    }

    @Override
    public List<UtilisateurIamEntity> recupererTousUtilisateurIam() {

        return utilisateurIamRepository.findAll();
    }

    @Override
    public UtilisateurIamEntity mettreAJourUtilisateurIam(UtilisateurIamEntity utilisateurIamEntity) {

        return utilisateurIamRepository.save(utilisateurIamEntity);
    }

    @Override
    public void supprimerUtilisateurIam(UtilisateurIamEntity utilisateurIamEntity) {

        utilisateurIamRepository.delete(utilisateurIamEntity);

    }
}
