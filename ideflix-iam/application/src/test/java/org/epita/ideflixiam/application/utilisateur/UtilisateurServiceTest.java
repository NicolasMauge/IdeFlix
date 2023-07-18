package org.epita.ideflixiam.application.utilisateur;

import org.epita.ideflixiam.application.exception.UtilisateurExistantDejaException;
import org.epita.ideflixiam.domaine.RoleEntity;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.epita.ideflixiam.infrastructure.RoleRepository;
import org.epita.ideflixiam.infrastructure.UtilisateurRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.epita.ideflixiam.application.common.UtileRole.ROLE_ADMIN;
import static org.epita.ideflixiam.application.common.UtileRole.ROLE_UTILISATEUR;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UtilisateurServiceImpl.class})
public class UtilisateurServiceTest {
    private static final RoleEntity roleAdmin = new RoleEntity(ROLE_ADMIN);
    private static final RoleEntity roleUtilisateur = new RoleEntity(ROLE_UTILISATEUR);
    private static final List<RoleEntity> listeRoleAdmin = List.of(roleAdmin, roleUtilisateur);
    private static final List<RoleEntity> listeRoleStandard = List.of(roleUtilisateur);

    private static UtilisateurEntity utilisateurAdmin;
    private static UtilisateurEntity utilisateurStandardExistant;
    private static UtilisateurEntity utilisateurStandardInexistant;

    @Autowired
    UtilisateurService utilisateurService;
    @MockBean
    UtilisateurRepository utilisateurRepositoryMock;
    @MockBean
    RoleRepository roleRepositoryMock;

    public UtilisateurServiceTest() {
    }

    @BeforeEach
    void setup() {

        utilisateurAdmin = new UtilisateurEntity(
                "TEST",
                "Admin",
                "admin@example.org",
                "123456",
                listeRoleAdmin);

        utilisateurStandardExistant = new UtilisateurEntity(
                "TEST",
                "Standard",
                "standard@example.org",
                "123456",
                listeRoleStandard);

        utilisateurStandardInexistant = new UtilisateurEntity(
                "TEST",
                "Standard-Inexistant",
                "standard_inexistant@example.org",
                "123456",
                listeRoleStandard);


        when(utilisateurRepositoryMock.findByEmail("standard@example.org")).thenReturn(Optional.of(utilisateurStandardExistant));
        when(utilisateurRepositoryMock.save(utilisateurStandardExistant)).thenReturn(utilisateurStandardExistant);

        when(utilisateurRepositoryMock.findByEmail("standard_inexistant@example.org")).thenReturn(Optional.ofNullable(null));
        when(utilisateurRepositoryMock.save(utilisateurStandardInexistant)).thenReturn(utilisateurStandardInexistant);


        when(utilisateurRepositoryMock.findByEmail("admin@example.org")).thenReturn(Optional.of(utilisateurAdmin));
        when(utilisateurRepositoryMock.save(utilisateurAdmin)).thenReturn(utilisateurAdmin);

    }


    @Test
    @DisplayName("Créer un utilisateur standard et vérifier ses rôles")
    void testCreationUtilisateurStandard() {
        UtilisateurEntity utilisateurEntity = utilisateurService.creerUtilisateur(utilisateurStandardInexistant);

        assertThat(utilisateurEntity.getNom()).isEqualTo("TEST");
        assertThat(utilisateurEntity.getPrenom()).isEqualTo("Standard-Inexistant");
        assertThat(utilisateurEntity.getListeRoleEntities()).contains(roleUtilisateur).doesNotContain(roleAdmin);
        assertThat(utilisateurEntity.getEmail()).isEqualTo("standard_inexistant@example.org");
    }

    @Test
    @DisplayName("Vérifier qu'on ne peut pas recréer un utilisateur")
    void testCreationUtilisateurExistant() {

        UtilisateurExistantDejaException thrown = Assertions.assertThrows(UtilisateurExistantDejaException.class, () -> {
            // When
            final UtilisateurEntity expected = utilisateurService.creerUtilisateur(utilisateurStandardExistant);
        });


        Assertions.assertEquals("L'utilisateur " + utilisateurStandardExistant.getEmail() + " existe déjà", thrown.getMessage());
    }


}
