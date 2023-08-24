package org.epita.exposition.controller.selection;

import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.MediaSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.controller.media.GenreController;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.dto.selection.MediaSelectionneCompletDto;
import org.epita.exposition.dto.selection.MediaSelectionneDto;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/mediaselectionne")
public class MediaSelectionneController {
    Mapper<FilmSelectionneEntity, MediaSelectionneDto> filmMapper;
    Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> filmCompletMapper;
    Mapper<SerieSelectionneeEntity, MediaSelectionneDto> serieMapper;
    Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> serieCompletMapper;

    GenreMapper genreMapper;

    private FilmSelectionneService filmSelectionneService;
    private SerieSelectionneeService serieSelectionneeService;

    public MediaSelectionneController(FilmSelectionneService filmSelectionneService, SerieSelectionneeService serieSelectionneeService, Mapper<FilmSelectionneEntity, MediaSelectionneDto> filmMapper, Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> filmCompletMapper, Mapper<SerieSelectionneeEntity, MediaSelectionneDto> serieMapper, Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> serieCompletMapper, GenreMapper genreMapper) {
        this.filmSelectionneService = filmSelectionneService;
        this.serieSelectionneeService = serieSelectionneeService;
        this.filmMapper = filmMapper;
        this.filmCompletMapper = filmCompletMapper;
        this.serieMapper = serieMapper;
        this.serieCompletMapper = serieCompletMapper;
        this.genreMapper = genreMapper;
    }

    @PostMapping()
    public ResponseEntity<String> creerMediaSelectionne(@RequestBody MediaSelectionneDto mediaSelectionneDto) {
        if (mediaSelectionneDto.getTypeMedia() == TypeMedia.FILM) {
            this.filmSelectionneService.creerFilmSelectionne(this.filmMapper.mapDtoToEntity(mediaSelectionneDto));
        } else if (mediaSelectionneDto.getTypeMedia() == TypeMedia.SERIE) {
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
        filmSelectionne.stream().forEach(f -> mediaDtoList.add(f));
        serieSelectionnee.stream().forEach(s -> mediaDtoList.add(s));

        return mediaDtoList;
    }

    @GetMapping("/utilisateur/{email}/genres")
    public TreeSet<GenreDto> trouverTousLesGenresParUtilisateur(@PathVariable("email") String email) {
        List<FilmSelectionneEntity> filmSelectionne = this.filmSelectionneService
                .trouverFilmsSelectionnesParEmailUtilisateur(email);
        List<SerieSelectionneeEntity> serieSelectionnee = this.serieSelectionneeService
                .trouverSeriesSelectionneesParEmailUtilisateur(email);

        List<GenreDto> genreDtoList = new ArrayList<>();
        filmSelectionne.forEach(f -> f.getMediaAudioVisuelEntity().getGenreList().forEach(genreEntity -> genreDtoList.add(genreMapper.mapEntityToDto(genreEntity))));
        serieSelectionnee.forEach(s -> s.getMediaAudioVisuelEntity().getGenreList().forEach(genreEntity -> genreDtoList.add(genreMapper.mapEntityToDto(genreEntity))));

        TreeSet<GenreDto> genreSet = new TreeSet<>((genre1, genre2) -> {
            if (genre1 == null || genre2 == null) return 1;
            if (genre1.getIdTmdb().equals(genre2.getIdTmdb()))
                return 0; // même id TMDB = identique
            else {
                int compare = genre1.getNomGenre().compareTo(genre2.getNomGenre());
                if (compare < 0) return -1;
                else if (compare > 0) return 1;
                else return 1; // on veut garder les doublons s'ils ont un id TMDB différents.
            }
        });
        genreSet.addAll(genreDtoList);
        return genreSet;
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
        if (films.size() > 0) {
            this.filmSelectionneService.supprimerFilmSelectionneParId(films.get(0).getId());
            return new ResponseEntity<>("Media sélectionné supprimé", HttpStatus.ACCEPTED);
        }

        List<SerieSelectionneeEntity> series = this.serieSelectionneeService.trouverSeriesSelectionneesParEmailUtilisateurEtIdTmdb(email, idTmdb);
        if (series.size() > 0) {
            this.serieSelectionneeService.supprimerSerieSelectionneeParId(series.get(0).getId());
            return new ResponseEntity<>("Media sélectionné supprimé", HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>("Media sélectionné supprimé", HttpStatus.ACCEPTED);
    }
}
