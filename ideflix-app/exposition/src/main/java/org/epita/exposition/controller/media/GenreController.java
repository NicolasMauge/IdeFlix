package org.epita.exposition.controller.media;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.epita.application.media.genre.GenreService;
import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.GenreDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@RestController
@RequestMapping("/genre")
public class GenreController {
    private GenreService genreService;
    private Mapper<GenreEntity, GenreDto> genreMapper;

    private FilmSelectionneService filmSelectionneService;
    private SerieSelectionneeService serieSelectionneeService;

    public GenreController(GenreService genreService, Mapper<GenreEntity, GenreDto> genreMapper, FilmSelectionneService filmSelectionneService, SerieSelectionneeService serieSelectionneeService) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
        this.filmSelectionneService = filmSelectionneService;
        this.serieSelectionneeService = serieSelectionneeService;
    }

    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck() {
        //return "UP";
        return new ResponseEntity<>("UP", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> creerGenre(@RequestBody GenreDto genreDto) {
        this.genreService
                .creerGenre(
                        this.genreMapper.mapDtoToEntity(genreDto));

        return new ResponseEntity<>("Genre créé", HttpStatus.CREATED);
    }

    @PostMapping("/masse")
    public ResponseEntity<String> creerPlusieursGenres(@RequestBody List<GenreDto> genreDtoList) {
        genreDtoList
                .stream()
                .forEach(g -> this.genreService
                        .creerGenre(
                                this.genreMapper
                                        .mapDtoToEntity(g)));

        return new ResponseEntity<>("Genres créés", HttpStatus.CREATED);
    }

    @GetMapping("/{idtmdb}")
    public GenreDto trouverGenreParIdTmdb(@PathVariable("idtmdb") String idTmdb) {
        return this.genreMapper
                .mapEntityToDto(
                        this.genreService.trouverGenreByIdTmdb(idTmdb));
    }


    @ApiOperation(value = "Récupération des genres des films et séries d'un utilisateur.",
            notes = "Chaque id TMDB est unique. Le résultat est trié par ordre alphabétique du nom du genre.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK."),
            @ApiResponse(code = 403, message = "Non autorisé."),
    })
    @GetMapping("/utilisateur/{email}")
    public ResponseEntity<TreeSet<GenreDto>> trouverGenreParEmailUtilisateur(@PathVariable("email") String email) {
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
                else if (genre1.getNomGenre().compareTo(genre2.getNomGenre()) > 0) return 1;
                else return -1;
            }
        });
        genreSet.addAll(genreDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(genreSet);
    }

    @GetMapping
    public List<GenreDto> trouverTousLesGenres() {
        return this.genreMapper
                .mapListEntityToDto(
                        this.genreService.trouverTousLesGenres());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerGenre(@PathVariable("id") Long id) {
        this.genreService.supprimerGenreParId(id);

        return new ResponseEntity<>("Genre supprimé", HttpStatus.OK);
    }
}
