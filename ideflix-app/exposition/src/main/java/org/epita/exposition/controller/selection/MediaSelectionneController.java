package org.epita.exposition.controller.selection;

import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.selection.MediaSelectionneCompletDto;
import org.epita.exposition.dto.selection.MediaSelectionneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mediaselectionne")
public class MediaSelectionneController {
    private FilmSelectionneService filmSelectionneService;
    private SerieSelectionneeService serieSelectionneeService;
    Mapper<FilmSelectionneEntity, MediaSelectionneDto> filmMapper;
    Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> filmCompletMapper;
    Mapper<SerieSelectionneeEntity, MediaSelectionneDto> serieMapper;
    Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> serieCompletMapper;

    public MediaSelectionneController(FilmSelectionneService filmSelectionneService, SerieSelectionneeService serieSelectionneeService, Mapper<FilmSelectionneEntity, MediaSelectionneDto> filmMapper, Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> filmCompletMapper, Mapper<SerieSelectionneeEntity, MediaSelectionneDto> serieMapper, Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> serieCompletMapper) {
        this.filmSelectionneService = filmSelectionneService;
        this.serieSelectionneeService = serieSelectionneeService;
        this.filmMapper = filmMapper;
        this.filmCompletMapper = filmCompletMapper;
        this.serieMapper = serieMapper;
        this.serieCompletMapper = serieCompletMapper;
    }

    @PostMapping()
    public ResponseEntity<String> creerMediaSelectionne(@RequestBody MediaSelectionneDto mediaSelectionneDto) {
        if(mediaSelectionneDto.getTypeMedia()== TypeMedia.FILM) {
            this.filmSelectionneService.creerFilmSelectionne(this.filmMapper.mapDtoToEntity(mediaSelectionneDto));
        }
        else if (mediaSelectionneDto.getTypeMedia()== TypeMedia.SERIE) {
            this.serieSelectionneeService.creerSerieSelectionnee(this.serieMapper.mapDtoToEntity(mediaSelectionneDto));
        }

        return new ResponseEntity<>("Media sélectionné créé", HttpStatus.CREATED);
    }

    @GetMapping("/utilisateur/{email}")
    public List<MediaSelectionneCompletDto> trouverTousLesMediaParUtilisateur(@PathVariable("email") String email) {
        List<MediaSelectionneCompletDto> filmSelectionne = this.filmCompletMapper.mapListEntityToDto(
                this.filmSelectionneService
                        .trouverFilmsSelectionnesParEmailUtilisateur(email));
        List<MediaSelectionneCompletDto> serieSelectionnee = this.serieCompletMapper.mapListEntityToDto(
                this.serieSelectionneeService
                        .trouverSeriesSelectionneesParEmailUtilisateur(email));

        List<MediaSelectionneCompletDto> mediaDtoList = new ArrayList<>();
        filmSelectionne.stream().forEach(f->mediaDtoList.add(f));
        serieSelectionnee.stream().forEach(s->mediaDtoList.add(s));

        return mediaDtoList;
    }

    @GetMapping("/utilisateur/{email}/idtmdb/{idTmdb}")
    public List<MediaSelectionneCompletDto> trouverMediasParUtilisateurEtIdTmdb(@PathVariable("email") String email,
                                                                                @PathVariable("idTmdb") String idTmdb) {
        List<FilmSelectionneEntity> filmList = this.filmSelectionneService.trouverFilmSelectionnesParEmailUtilisateurEtIdTmdb(email, idTmdb);

        if (filmList.size() > 0) {
            return this.filmCompletMapper.mapListEntityToDto(filmList);
        }

        List<SerieSelectionneeEntity> serieList = this.serieSelectionneeService.trouverSeriesSelectionneesParEmailUtilisateurEtIdTmdb(email, idTmdb);

        if (serieList.size() > 0) {
            return this.serieCompletMapper.mapListEntityToDto(serieList);
        }

        return new ArrayList<>();
    }

    @DeleteMapping("/{email}/{idTmdb}")
    public ResponseEntity<String> supprimerMediaSelectionneePourIdTmdb(@PathVariable("email") String email,
                                                                       @PathVariable("idTmdb") String idTmdb) {
        System.out.println("suppression");
        List<FilmSelectionneEntity> films = this.filmSelectionneService.trouverFilmSelectionnesParEmailUtilisateurEtIdTmdb(email, idTmdb);
        if(films.size() > 0) {
            this.filmSelectionneService.supprimerFilmSelectionneParId(films.get(0).getId());
            return new ResponseEntity<>("Media sélectionné supprimé", HttpStatus.ACCEPTED);
        }

        List<SerieSelectionneeEntity> series = this.serieSelectionneeService.trouverSeriesSelectionneesParEmailUtilisateurEtIdTmdb(email, idTmdb);
        if(series.size() > 0) {
            this.serieSelectionneeService.supprimerSerieSelectionneeParId(series.get(0).getId());
            return new ResponseEntity<>("Media sélectionné supprimé", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Media sélectionné supprimé", HttpStatus.ACCEPTED);
    }
}
