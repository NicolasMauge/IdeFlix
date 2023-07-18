package org.epita.exposition.dto.selection.etiquette;

import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreMapper;
import org.epita.exposition.media.serie.SerieMapper;
import org.epita.exposition.selection.etiquette.EtiquetteDto;
import org.epita.exposition.selection.etiquette.EtiquetteMapper;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {EtiquetteMapper.class})
@ContextConfiguration(classes = {UtilisateurMapper.class})
public class EtiquetteMapperTest {
    @Autowired
    Mapper<EtiquetteEntity, EtiquetteDto> mapper;

    @Test
    public void should_return_mapEntityToDto() {
        // Given
        // Utilisateur
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);

        // Etiquette
        EtiquetteEntity etiquette = new EtiquetteEntity(1L, "tag 1", utilisateur );

        // When
        EtiquetteDto etiquetteDto = this.mapper.mapEntityToDto(etiquette);

        // Then
        assertThat(etiquetteDto.getNomTag())
                .isEqualTo(etiquette.getNomTag());

        assertThat(etiquetteDto.getIdUtilisateur())
                .isEqualTo(etiquette.getUtilisateurEntity().getId());
    }

    @Test
    public void should_return_mapDtoToEntity() {
        // Given
        EtiquetteDto etiquetteDto = new EtiquetteDto("tag 1", 1L);

        // When
        EtiquetteEntity etiquette = this.mapper.mapDtoToEntity(etiquetteDto);

        // Then
        assertThat(etiquette.getId())
                .isEqualTo(null);

        assertThat(etiquette.getNomTag())
                .isEqualTo(etiquetteDto.getNomTag());

        assertThat(etiquette.getUtilisateurEntity().getId())
                .isEqualTo(etiquetteDto.getIdUtilisateur());
    }
}
