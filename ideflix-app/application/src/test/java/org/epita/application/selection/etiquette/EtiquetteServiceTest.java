package org.epita.application.selection.etiquette;

import org.epita.application.mediaDataBase.genreDataBase.GenreDataBaseService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.selection.EtiquetteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EtiquetteServiceImpl.class, GenreDataBaseService.class})
public class EtiquetteServiceTest {
    @Autowired
    EtiquetteService etiquetteService;

    @MockBean
    EtiquetteRepository repositoryMock;

    EtiquetteEntity etiquette;

    UtilisateurEntity utilisateur;

    @BeforeEach
    public void setUp() {
        // définition de l'utilisateur
        utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        // définition de l'étiquette utilisée pour les test
        etiquette = new EtiquetteEntity();
        etiquette.setId(1L);
        etiquette.setNomTag("tag 1");
        etiquette.setUtilisateurEntity(this.utilisateur);

        etiquetteService.creerEtiquette(etiquette);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(etiquette));
    }

    @Test
    public void creerEtiquette_should_call_save_repository_1_time() {
        // Then
        verify(repositoryMock, times(1)).save(etiquette);
    }

    @Test
    public void trouverEtiquetteParId_shoudl_return_1_element() {
        // When
        final EtiquetteEntity expected = this.etiquetteService.trouverEtiquetteParId(1L);

        // Then
        assertThat(expected).isEqualTo(etiquette);
    }

    @Test
    public void trouverToutesLesEtiquettes_should_return_2_elements() {
        // Given
        EtiquetteEntity etiquette2 = new EtiquetteEntity();
        etiquette2.setId(2L);
        etiquette2.setUtilisateurEntity(this.utilisateur);

        etiquetteService.creerEtiquette(etiquette2);

        List<EtiquetteEntity> entities = new ArrayList<>();

        entities.add(etiquette);
        entities.add(etiquette2);

        when(repositoryMock.findAll()).thenReturn(entities);

        // When
        final List<EtiquetteEntity> trouves = this.etiquetteService.trouverToutesLesEtiquettes();

        // Then
        assertThat(trouves).hasSize(2);
    }

    @Test
    public void trouverEtiquetteParId_should_throw_exception() {
        EntityNotFoundException thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            // When
            final EtiquetteEntity expected = this.etiquetteService.trouverEtiquetteParId(10L);
        });

        Assertions.assertEquals("Etiquette non trouvée", thrown.getMessage());
    }
}
