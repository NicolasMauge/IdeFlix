package org.epita.exposition.selection.serieselectionnee;

import org.epita.application.media.film.FilmService;
import org.epita.application.media.serie.SerieService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.MediaAudioVisuelEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.selection.etiquette.EtiquetteDto;
import org.epita.exposition.selection.filmselectionne.FilmSelectionneDto;
import org.springframework.stereotype.Component;

@Component
public class SerieSelectionneeMapper extends Mapper<SerieSelectionneeEntity, SerieSelectionneeDto> {
    private Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    private SerieService serieService;
    private UtilisateurService utilisateurService;

    public SerieSelectionneeMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, SerieService serieService, UtilisateurService utilisateurService) {
        this.etiquetteMapper = etiquetteMapper;
        this.serieService = serieService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public SerieSelectionneeDto mapEntityToDto(SerieSelectionneeEntity input) {
        return new SerieSelectionneeDto(
                input.getId(),
                input.getAvisPouce(),
                input.getDateSelection(),
                etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                input.getMediaAudioVisuelEntity().getIdTmdb(),
                input.getUtilisateurEntity().getId(),
                input.getDateModification(),
                input.getNumeroSaison(),
                input.getIdTmdbSaison(),
                input.getNumeroEpisode(),
                input.getIdTmdbEpisode()
        );
    }

    @Override
    public SerieSelectionneeEntity mapDtoToEntity(SerieSelectionneeDto input) {
        SerieEntity serie = this.serieService.trouverSerieParIdTmdb(input.getIdTmdb());
        UtilisateurEntity utilisateur = this.utilisateurService.trouverUtilisateurParId(input.getIdUtilisateur());

        return new SerieSelectionneeEntity(
                input.getAvisPouce(),
                input.getDateSelection(),
                etiquetteMapper.mapListDtoToEntity(input.getEtiquetteList()),
                input.getStatutMedia(),
                serie,
                utilisateur,
                input.getDateModification(),
                input.getNumeroSaison(),
                input.getIdTmdbSaison(),
                input.getNumeroEpisode(),
                input.getIdTmdbEpisode()
        );
    }
}
