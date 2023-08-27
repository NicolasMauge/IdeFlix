package org.epita.exposition.mapper.selection.filmselectionne;

import org.epita.application.media.film.FilmServiceImpl;
import org.epita.application.utilisateur.utilisateur.UtilisateurServiceImpl;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.StatutMediaEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.FilmDto;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.mapper.selection.etiquette.EtiquetteMapper;
import org.epita.exposition.dto.selection.FilmSelectionneDto;
import org.epita.exposition.mapper.selection.film.FilmSelectionneMapper;
import org.epita.exposition.dto.utilisateur.UtilisateurDto;
import org.epita.exposition.mapper.utilisateur.UtilisateurMapper;
import org.epita.infrastructure.media.FilmRepository;
import org.epita.infrastructure.media.GenreRepository;
import org.epita.infrastructure.utilisateur.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {FilmSelectionneMapper.class})
@ContextConfiguration(classes = {EtiquetteMapper.class, UtilisateurMapper.class,
        FilmServiceImpl.class, UtilisateurServiceImpl.class})
public class FilmSelectionneMapperTest {
    @Autowired
    Mapper<FilmSelectionneEntity, FilmSelectionneDto> mapper;

    @MockBean
    FilmRepository filmRepositoryMock;

    @MockBean
    UtilisateurRepository utilisateurRepositoryMock;

    @MockBean
    GenreRepository genreRepository;

    @Test
    public void should_return_mapEntityToDto() {
        // Given
        // Utilisateur
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);

        // Liste de genres
        List<GenreEntity> genreEntityList = new ArrayList<>();
        genreEntityList
                .add(new GenreEntity(1L, "1535-Dfetf432", "nom genre"));
        genreEntityList.add(
                new GenreEntity(2L, "675-dfg-456", "nom genre 2"));

        // MediaAudiovisuel
        FilmEntity film = new FilmEntity();
        film.setIdTmdb("123-DFG-567");

        // Etiquette
        List<EtiquetteEntity> etiquetteList = new ArrayList<>();
        etiquetteList.add(
                new EtiquetteEntity(1L, "tag 1", utilisateur));
        etiquetteList.add(
                new EtiquetteEntity(2L, "tag 2", utilisateur));

        // Film
        FilmSelectionneEntity filmSelectionne = new FilmSelectionneEntity();
        filmSelectionne.setId(1L);
        filmSelectionne.setAvisPouce(true);
        filmSelectionne.setDateSelection(LocalDate.of(2023,07,13));
        filmSelectionne.setEtiquetteEntityList(etiquetteList);
        filmSelectionne.setUtilisateurEntity(utilisateur);
        filmSelectionne.setMediaAudioVisuelEntity(film);
        filmSelectionne.setStatutMediaEntity(StatutMediaEntity.ABANDONNE);

        // When
        FilmSelectionneDto filmSelectionneDto = this.mapper.mapEntityToDto(filmSelectionne);

        // Then
        // données propres à film sélectionné
        assertThat(filmSelectionneDto.getAvisPouce())
                .isEqualTo(filmSelectionne.getAvisPouce());

        assertThat(filmSelectionneDto.getDateSelection())
                .isEqualTo(filmSelectionne.getDateSelection());

        // données propres au film (mises à plat dans le mapper)
        assertThat(filmSelectionneDto.getIdTmdb())
                .isEqualTo(filmSelectionne.getMediaAudioVisuelEntity().getIdTmdb());
    }

    @Test
    public void should_return_mapDtoToEntity() {
        // Given
        // Utilisateur
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1L);
        // Etiquette
        List<EtiquetteDto> etiquetteDtoList = new ArrayList<>();
        // rem : pas d'id pour EtiquetteDto
        etiquetteDtoList.add(
                new EtiquetteDto(1L, "tag 1", 1L));
        etiquetteDtoList.add(
                new EtiquetteDto(2L, "tag 2", 1L));

        // MediaAudiovisuel
        FilmDto filmDto = new FilmDto();
        filmDto.setIdTmdb("123-DFG-567");

        // FilmSelectionne
        FilmSelectionneDto filmSelectionneDto = new FilmSelectionneDto();
        filmSelectionneDto.setAvisPouce(true);
        filmSelectionneDto.setDateSelection(LocalDate.of(2023,07,13));
        filmSelectionneDto.setEtiquetteList(etiquetteDtoList);
        filmSelectionneDto.setIdUtilisateur(utilisateurDto.getId());
        filmSelectionneDto.setIdTmdb(filmDto.getIdTmdb());
        filmSelectionneDto.setStatutMedia(StatutMediaEntity.ABANDONNE);

        // paramétrage spécifique du mock de repository pour le film cherché dans le mapper (via service)
        FilmEntity film = new FilmEntity();
        film.setIdTmdb(filmDto.getIdTmdb());
        when(filmRepositoryMock.findByIdTmdb(filmDto.getIdTmdb())).thenReturn(Optional.of(film));

        // paramétrage spécifique du mock de repository pour l'utilisateur cherché dans le mapper (via service)
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(utilisateurDto.getId());
        when(utilisateurRepositoryMock.findById(utilisateurDto.getId())).thenReturn(Optional.of(utilisateur));

        // When
        FilmSelectionneEntity filmSelectionne = this.mapper.mapDtoToEntity(filmSelectionneDto);

        // Then
        assertThat(filmSelectionne.getAvisPouce())
                .isEqualTo(filmSelectionneDto.getAvisPouce());

        assertThat(filmSelectionne.getDateSelection())
                .isEqualTo(filmSelectionneDto.getDateSelection());

        assertThat(filmSelectionne.getEtiquetteEntityList().size())
                .isEqualTo(filmSelectionneDto.getEtiquetteList().size());

        assertThat(filmSelectionne.getUtilisateurEntity().getId())
                .isEqualTo(filmSelectionneDto.getIdUtilisateur());

        assertThat(filmSelectionne.getMediaAudioVisuelEntity().getIdTmdb())
                .isEqualTo(filmSelectionneDto.getIdTmdb());

        assertThat(filmSelectionne.getStatutMediaEntity())
                .isEqualTo(filmSelectionneDto.getStatutMedia());
    }
}
