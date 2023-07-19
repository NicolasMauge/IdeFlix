package org.epita.exposition.dto.selection.serieselectionnee;

import org.epita.application.media.serie.SerieServiceImpl;
import org.epita.application.utilisateur.utilisateur.UtilisateurServiceImpl;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.selection.StatutMediaEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.SerieDto;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.mapper.selection.etiquette.EtiquetteMapper;
import org.epita.exposition.dto.selection.SerieSelectionneeDto;
import org.epita.exposition.mapper.selection.serie.SerieSelectionneeMapper;
import org.epita.exposition.dto.utilisateur.UtilisateurDto;
import org.epita.exposition.mapper.utilisateur.UtilisateurMapper;
import org.epita.infrastructure.media.SerieRepository;
import org.epita.infrastructure.utilisateur.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
@SpringBootTest(classes = {SerieSelectionneeMapper.class})
@ContextConfiguration(classes = {EtiquetteMapper.class, UtilisateurMapper.class,
        SerieServiceImpl.class, UtilisateurServiceImpl.class})
public class SerieSelectionneeMapperTest {
    @Autowired
    Mapper<SerieSelectionneeEntity, SerieSelectionneeDto> mapper;

    @MockBean
    SerieRepository serieRepository;

    @MockBean
    UtilisateurRepository utilisateurRepositoryMock;

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
        SerieEntity serie = new SerieEntity();
        serie.setIdTmdb("123-DFG-567");

        // Etiquette
        List<EtiquetteEntity> etiquetteList = new ArrayList<>();
        etiquetteList.add(
                new EtiquetteEntity(1L, "tag 1", utilisateur));
        etiquetteList.add(
                new EtiquetteEntity(2L, "tag 2", utilisateur));

        // Film
        SerieSelectionneeEntity serieSelectionnee = new SerieSelectionneeEntity();
        serieSelectionnee.setId(1L);
        serieSelectionnee.setAvisPouce(true);
        serieSelectionnee.setDateSelection(LocalDate.of(2023,07,13));
        serieSelectionnee.setEtiquetteEntityList(etiquetteList);
        serieSelectionnee.setUtilisateurEntity(utilisateur);
        serieSelectionnee.setMediaAudioVisuelEntity(serie);
        serieSelectionnee.setStatutMediaEntity(StatutMediaEntity.ABANDONNE);

        // When
        SerieSelectionneeDto serieSelectionneeDto = this.mapper.mapEntityToDto(serieSelectionnee);

        // Then
        // données propres à film sélectionné
        assertThat(serieSelectionneeDto.getAvisPouce())
                .isEqualTo(serieSelectionnee.getAvisPouce());

        assertThat(serieSelectionneeDto.getDateSelection())
                .isEqualTo(serieSelectionnee.getDateSelection());

        // données propres au film (mises à plat dans le mapper)
        assertThat(serieSelectionneeDto.getIdTmdb())
                .isEqualTo(serieSelectionnee.getMediaAudioVisuelEntity().getIdTmdb());
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
                new EtiquetteDto("tag 1", 1L));
        etiquetteDtoList.add(
                new EtiquetteDto("tag 2", 1L));

        // MediaAudiovisuel
        SerieDto serieDto = new SerieDto();
        serieDto.setIdTmdb("123-DFG-567");

        // FilmSelectionne
        SerieSelectionneeDto selectionneeDto = new SerieSelectionneeDto();
        selectionneeDto.setAvisPouce(true);
        selectionneeDto.setDateSelection(LocalDate.of(2023,07,13));
        selectionneeDto.setEtiquetteList(etiquetteDtoList);
        selectionneeDto.setIdUtilisateur(utilisateurDto.getId());
        selectionneeDto.setIdTmdb(serieDto.getIdTmdb());
        selectionneeDto.setStatutMedia(StatutMediaEntity.ABANDONNE);

        // paramétrage spécifique du mock de repository pour le film cherché dans le mapper (via service)
        SerieEntity serie = new SerieEntity();
        serie.setIdTmdb(serieDto.getIdTmdb());
        when(serieRepository.findByIdTmdb(serieDto.getIdTmdb())).thenReturn(Optional.of(serie));

        // paramétrage spécifique du mock de repository pour l'utilisateur cherché dans le mapper (via service)
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(utilisateurDto.getId());
        when(utilisateurRepositoryMock.findById(utilisateurDto.getId())).thenReturn(Optional.of(utilisateur));

        // When
        SerieSelectionneeEntity serieSelectionnee = this.mapper.mapDtoToEntity(selectionneeDto);

        // Then
        assertThat(serieSelectionnee.getAvisPouce())
                .isEqualTo(selectionneeDto.getAvisPouce());

        assertThat(serieSelectionnee.getDateSelection())
                .isEqualTo(selectionneeDto.getDateSelection());

        assertThat(serieSelectionnee.getEtiquetteEntityList().size())
                .isEqualTo(selectionneeDto.getEtiquetteList().size());

        assertThat(serieSelectionnee.getUtilisateurEntity().getId())
                .isEqualTo(selectionneeDto.getIdUtilisateur());

        assertThat(serieSelectionnee.getMediaAudioVisuelEntity().getIdTmdb())
                .isEqualTo(selectionneeDto.getIdTmdb());

        assertThat(serieSelectionnee.getStatutMediaEntity())
                .isEqualTo(selectionneeDto.getStatutMedia());
    }
}
