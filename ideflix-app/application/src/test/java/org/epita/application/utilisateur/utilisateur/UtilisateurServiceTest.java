package org.epita.application.utilisateur.utilisateur;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.utilisateur.UtilisateurRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UtilisateurServiceImpl.class })
public class UtilisateurServiceTest {
    @Autowired
    UtilisateurService utilisateurService;

    @MockBean
    UtilisateurRepository utilisateurRepositoryMock;

    UtilisateurEntity utilisateur;

    @Before
    public void setUp() {
        utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prénom");

        PreferencesUtilisateurEntity preferencesUtilisateurEntity = new PreferencesUtilisateurEntity();
        preferencesUtilisateurEntity.setPseudo("pseudo 1");

        utilisateur.setPreferencesUtilisateurEntity(preferencesUtilisateurEntity);

        utilisateurService.creerUtilisateur(utilisateur);

        when(utilisateurRepositoryMock.findById(1L)).thenReturn(Optional.of(utilisateur));
        when(utilisateurRepositoryMock.findByEmail(utilisateur.getEmail())).thenReturn(Optional.of(utilisateur));
    }

    @Test
    public void creerUtilisateur_should_call_save_repository_1_time() {
        // Given

        // When

        // Then
        verify(utilisateurRepositoryMock,times(1)).save(utilisateur);
    }

    @Test
    public void trouverUtilisateurParId_should_return_1_element()  {
        // Given

        // When
        final UtilisateurEntity expected = this.utilisateurService.trouverUtilisateurParId(1L);

        // Then
        assertThat(expected).isEqualTo(utilisateur);
    }

    @Test
    public void trouverUtilisateurParEmail_should_return_1_element()  {
        // Given

        // When
        final UtilisateurEntity expected = this.utilisateurService.trouverUtilisateurParEmail(utilisateur.getEmail());

        // Then
        assertThat(expected).isEqualTo(utilisateur);
    }

    @Test
    public void trouverTousLesUtilisateurs_should_return_2_elements() {
        // Given
        UtilisateurEntity utilisateur_2 = new UtilisateurEntity();
        utilisateur_2.setId(2L);
        utilisateur_2.setEmail("test2@test.com");
        utilisateur_2.setNom("Nom 2");
        utilisateur_2.setPrenom("Prénom 2");
        utilisateurService.creerUtilisateur(utilisateur_2);

        List<UtilisateurEntity> utilisateurs = new ArrayList<>();
        utilisateurs.add(utilisateur);
        utilisateurs.add(utilisateur_2);

        when(utilisateurRepositoryMock.findAll()).thenReturn(utilisateurs);

        // When
        final List<UtilisateurEntity> utilisateurEntityList = this.utilisateurService.trouverTousLesUtilisateurs();

        // Then
        assertThat(utilisateurEntityList).hasSize(2);
    }

    @Test(expected = EntityNotFoundException.class)
    public void trouverUtilisateurParId_should_throw_exception() {
        // When
        final UtilisateurEntity expected = this.utilisateurService.trouverUtilisateurParId(10L);
    }
}
