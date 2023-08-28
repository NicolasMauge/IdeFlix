package org.epita.exposition.controller.selection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.epita.application.selection.filmselectionne.FilmSelectionneService;
import org.epita.application.selection.serieselectionnee.SerieSelectionneeService;
import org.epita.domaine.common.IamErreurHabilitationException;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.MediaSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.exposition.common.ErrorModel;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.controller.media.GenreController;
import org.epita.exposition.dto.common.TypeMedia;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.dto.selection.MediaSelectionneCompletDto;
import org.epita.exposition.dto.selection.MediaSelectionneDto;
import org.epita.exposition.iam.securite.Habilitations;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/mediaselectionne")
@Tag(name = "Sélection / Média")
public class MediaSelectionneController {
    Mapper<FilmSelectionneEntity, MediaSelectionneDto> filmMapper;
    Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> filmCompletMapper;
    Mapper<SerieSelectionneeEntity, MediaSelectionneDto> serieMapper;
    Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> serieCompletMapper;

    private FilmSelectionneService filmSelectionneService;
    private SerieSelectionneeService serieSelectionneeService;

    public MediaSelectionneController(FilmSelectionneService filmSelectionneService, SerieSelectionneeService serieSelectionneeService, Mapper<FilmSelectionneEntity, MediaSelectionneDto> filmMapper, Mapper<FilmSelectionneEntity, MediaSelectionneCompletDto> filmCompletMapper, Mapper<SerieSelectionneeEntity, MediaSelectionneDto> serieMapper, Mapper<SerieSelectionneeEntity, MediaSelectionneCompletDto> serieCompletMapper) {
        this.filmSelectionneService = filmSelectionneService;
        this.serieSelectionneeService = serieSelectionneeService;
        this.filmMapper = filmMapper;
        this.filmCompletMapper = filmCompletMapper;
        this.serieMapper = serieMapper;
        this.serieCompletMapper = serieCompletMapper;
    }

    @PostMapping()
    @Operation(summary = "Ajouter un média sélectionné.",
            method = "creerMediaSelectionne",
            description = "Ajout d'un média dans la sélection de l'utilisateur. Seul l'utilisateur lui-même est autorisé à ajouter un film ou une série dans sa sélection.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK.", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé. L'email du demandeur n'est pas l'email fourni.", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
    })
    public ResponseEntity<String> creerMediaSelectionne(@RequestBody MediaSelectionneDto mediaSelectionneDto) throws IamErreurHabilitationException {

        if (mediaSelectionneDto == null || mediaSelectionneDto.getEmail().isEmpty() || Habilitations.isHabilitationCorrecte(mediaSelectionneDto.getEmail())) {

            if (mediaSelectionneDto.getTypeMedia() == TypeMedia.FILM) {
                this.filmSelectionneService.creerFilmSelectionne(this.filmMapper.mapDtoToEntity(mediaSelectionneDto));
            } else if (mediaSelectionneDto.getTypeMedia() == TypeMedia.SERIE) {
                this.serieSelectionneeService.creerSerieSelectionnee(this.serieMapper.mapDtoToEntity(mediaSelectionneDto));
            }

            return new ResponseEntity<>("Media sélectionné créé", HttpStatus.CREATED);
        } else
            throw new IamErreurHabilitationException("IdeFlix - " + mediaSelectionneDto.getEmail() + " non habilité");
    }

    @Operation(summary = "Récupération des médias d'un utilisateur.",
            method = "trouverTousLesMediaParUtilisateur",
            description = "Seul l'utilisateur lui-même est autorisé à récupérer ses médias sélectionnés (films et séries).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé. L'email du demandeur n'est pas l'email demandé.", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
    })
    @GetMapping("/utilisateur/{email}")
    public List<MediaSelectionneCompletDto> trouverTousLesMediaParUtilisateur(@PathVariable("email") String email) throws IamErreurHabilitationException {

        if (Habilitations.isHabilitationCorrecte(email)) {
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
        } else
            throw new IamErreurHabilitationException("IdeFlix - " + email + " non habilité");
    }


    @Operation(summary = "Récupération des médias d'un utilisateur pour un identifiant TMDB donné.",
            method = "trouverMediasParUtilisateurEtIdTmdb",
            description = "Seul l'utilisateur lui-même est autorisé à récupérer ses médias sélectionnés (films et séries).")
    @GetMapping("/utilisateur/{email}/idtmdb/{idTmdb}")
    public List<MediaSelectionneCompletDto> trouverMediasParUtilisateurEtIdTmdb(@PathVariable("email") String email,
                                                                                @PathVariable("idTmdb") String idTmdb)
            throws IamErreurHabilitationException {

        if (Habilitations.isHabilitationCorrecte(email)) {
            List<FilmSelectionneEntity> filmList = this.filmSelectionneService.trouverFilmSelectionnesParEmailUtilisateurEtIdTmdb(email, idTmdb);

            if (filmList.size() > 0) {
                return this.filmCompletMapper.mapListEntityToDto(filmList);
            }

            List<SerieSelectionneeEntity> serieList = this.serieSelectionneeService.trouverSeriesSelectionneesParEmailUtilisateurEtIdTmdb(email, idTmdb);

            if (serieList.size() > 0) {
                return this.serieCompletMapper.mapListEntityToDto(serieList);
            }

            return new ArrayList<>();
        } else
            throw new IamErreurHabilitationException("IdeFlix - " + email + " non habilité");
    }

    @Operation(summary = "Effacer un film d'un utilisateur.",
            method = "supprimerMediaSelectionneePourIdTmdb",
            description = "Seul l'utilisateur lui-même est autorisé à effacer un média sélectionné.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK."),
            @ApiResponse(responseCode = "403", description = "Utilisateur non autorisé. L'email du demandeur n'est pas l'email demandé.", content = @Content(schema = @Schema(implementation = ErrorModel.class))),
    })
    @DeleteMapping("/{email}/{idTmdb}")
    public ResponseEntity<String> supprimerMediaSelectionneePourIdTmdb(@PathVariable("email") String email,
                                                                       @PathVariable("idTmdb") String idTmdb)
            throws IamErreurHabilitationException {

        if (Habilitations.isHabilitationCorrecte(email)) {
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
        } else
            throw new IamErreurHabilitationException("IdeFlix - " + email + " non habilité");
    }
}
