package org.epita.exposition.selection.serieselectionnee;

import org.epita.application.media.film.FilmService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.MediaAudioVisuelEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.selection.etiquette.EtiquetteDto;
import org.epita.exposition.selection.filmselectionne.FilmSelectionneDto;
import org.springframework.stereotype.Component;

@Component
public class SerieSelectionneeMapper extends Mapper<SerieSelectionneeEntity, SerieSelectionneeDto> {
    private Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    private FilmService filmService;
    private UtilisateurService utilisateurService;

    public SerieSelectionneeMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, FilmService filmService, UtilisateurService utilisateurService) {
        this.etiquetteMapper = etiquetteMapper;
        this.filmService = filmService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public SerieSelectionneeDto mapEntityToDto(SerieSelectionneeEntity input) {
        return new SerieSelectionneeDto(
                input.getAvisPouce(),
                input.getDateSelection(),
                etiquetteMapper.mapListEntityToDto(input.getEtiquetteEntityList()),
                input.getStatutMediaEntity(),
                input.getMediaAudioVisuelEntity().getId(),
                input.getMediaAudioVisuelEntity().getIdTmdb(),
                input.getMediaAudioVisuelEntity().getTitre(),
                input.getMediaAudioVisuelEntity().getDateSortie(),
                input.getMediaAudioVisuelEntity().getDuree(),
                input.getMediaAudioVisuelEntity().getCheminAffichePortrait(),
                input.getMediaAudioVisuelEntity().getCheminAffichePaysage(),
                input.getMediaAudioVisuelEntity().getNoteTmdb(),
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
        FilmEntity filmEntity = this.filmService.trouverFilmParId(input.getIdFilm());
        UtilisateurEntity utilisateurEntity = this.utilisateurService.trouverUtilisateurParId(input.getIdUtilisateur());

        return new SerieSelectionneeEntity(
                input.getAvisPouce(),
                input.getDateSelection(),
                etiquetteMapper.mapListDtoToEntity(input.getEtiquetteList()),
                input.getStatutMedia(),
                (MediaAudioVisuelEntity) filmEntity,
                utilisateurEntity,
                input.getDateModification(),
                input.getNumeroSaison(),
                input.getIdTmdbSaison(),
                input.getNumeroEpisode(),
                input.getIdTmdbEpisode()
        );
    }
}
