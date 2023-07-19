package org.epita.exposition.controller.selection;

import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.selection.MediaSelectionneCompletDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mediaselectionne")
public class MediaSelectionneController {
    private FilmSelectionneService filmSelectionneService;
    private SerieSelectionneeService serieSelectionneeService;
    Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> filmMapper;
    Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> serieMapper;

    public MediaSelectionneController(FilmSelectionneService filmSelectionneService, SerieSelectionneeService serieSelectionneeService, Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> filmMapper, Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> serieMapper) {
        this.filmSelectionneService = filmSelectionneService;
        this.serieSelectionneeService = serieSelectionneeService;
        this.filmMapper = filmMapper;
        this.serieMapper = serieMapper;
    }

    @GetMapping("/utilisateur/{email}")
    public List<MediaSelectionneCompletDto> trouverTousLesMediaParUtilisateur(@PathVariable("email") String email) {
        List<MediaSelectionneCompletDto> filmSelectionne = this.filmMapper.mapListEntityToDto(
                this.filmSelectionneService
                        .trouverFilmsSelectionnesParEmailUtilisateur(email));
        List<MediaSelectionneCompletDto> serieSelectionnee = this.serieMapper.mapListEntityToDto(
                this.serieSelectionneeService
                        .trouverSeriesSelectionneesParEmailUtilisateur(email));

        List<MediaSelectionneCompletDto> mediaDtoList = new ArrayList<>();
        filmSelectionne.stream().forEach(f->mediaDtoList.add(f));
        serieSelectionnee.stream().forEach(s->mediaDtoList.add(s));

        return mediaDtoList;
    }
}
