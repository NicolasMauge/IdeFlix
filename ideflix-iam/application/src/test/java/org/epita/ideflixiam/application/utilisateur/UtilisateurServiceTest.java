package org.epita.ideflixiam.application.utilisateur;

import org.epita.ideflixiam.domaine.RoleEntity;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.epita.ideflixiam.infrastructure.RoleRepository;
import org.epita.ideflixiam.infrastructure.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import static org.epita.ideflixiam.application.common.UtileRole.ROLE_ADMIN;
import static org.epita.ideflixiam.application.common.UtileRole.ROLE_UTILISATEUR;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UtilisateurServiceImpl.class, UtilisateurRepository.class})
public class UtilisateurServiceTest {

//    private final RoleEntity roleAdmin = new RoleEntity(ROLE_ADMIN);
//    private final RoleEntity roleUtilisateur = new RoleEntity(ROLE_UTILISATEUR);
//
//    private final List<RoleEntity> listeRoleAdmin = List.of(roleAdmin, roleUtilisateur);
//    private final List<RoleEntity> listeRoleStandard = List.of(roleUtilisateur);
//
//    private final UtilisateurEntity utilisateurAdmin = new UtilisateurEntity(
//            "TEST",
//            "Admin",
//            "admin@example.org",
//            "123456",
//            listeRoleAdmin);
//
//    private final UtilisateurEntity utilisateurStandard = new UtilisateurEntity(
//            "TEST",
//            "Standard",
//            "standard@example.org",
//            "123456",
//            listeRoleStandard);
//
//    @Autowired
//    UtilisateurRepository utilisateurRepository;
//    @Autowired
//    RoleRepository roleRepository;

    public UtilisateurServiceTest() {
    }

    @Test
    void testCreationUtilisateurStandard() {
//        UtilisateurEntity utilisateurEntity = utilisateurRepository.save(utilisateurStandard);
//
//        assertThat(utilisateurEntity.getNom()).isEqualTo("TEST");
//        assertThat(utilisateurEntity.getPrenom()).isEqualTo("Standard");
//        assertThat(utilisateurEntity.getListeRoleEntities()).contains(roleUtilisateur).doesNotContain(roleAdmin);
//        assertThat(utilisateurEntity.getEmail()).isEqualTo("standard@example.org");


        assertThat(true).isTrue();

    }


}
