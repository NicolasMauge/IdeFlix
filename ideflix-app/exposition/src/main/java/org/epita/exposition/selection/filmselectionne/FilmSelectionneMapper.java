package org.epita.exposition.selection.filmselectionne;

import org.epita.application.media.film.FilmService;
import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.MediaAudioVisuelEntity;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.selection.etiquette.EtiquetteDto;
import org.springframework.stereotype.Component;

@Component
public class FilmSelectionneMapper extends Mapper<FilmSelectionneEntity, FilmSelectionneDto> {
    private Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper;
    private FilmService filmService;
    private UtilisateurService utilisateurService;

    public FilmSelectionneMapper(Mapper<EtiquetteEntity, EtiquetteDto> etiquetteMapper, FilmService filmService, UtilisateurService utilisateurService) {
        this.etiquetteMapper = etiquetteMapper;
        this.filmService = filmService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public FilmSelectionneDto mapEntityToDto(FilmSelectionneEntity input) {
        // on aplatit la table FilmSelectionne pour tout mettre dans le même objet
        // cela permet d'éviter notamment les problèmes de constructeur sur la classe abstraite
        return new FilmSelectionneDto(
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
                input.getUtilisateurEntity().getId()
        );
    }

    @Override
    public FilmSelectionneEntity mapDtoToEntity(FilmSelectionneDto input) {
        FilmEntity filmEntity = this.filmService.trouverFilmParId(input.getIdFilm());
        UtilisateurEntity utilisateurEntity = this.utilisateurService.trouverUtilisateurParId(input.getIdUtilisateur());

        return new FilmSelectionneEntity(
                input.getAvisPouce(),
                input.getDateSelection(),
                etiquetteMapper.mapListDtoToEntity(input.getEtiquetteList()),
                input.getStatutMedia(),
                (MediaAudioVisuelEntity) filmEntity,
                utilisateurEntity
        );
    }
}
