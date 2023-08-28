package org.epita.application.mediaDataBase.movieDataBase;

import org.epita.application.mediaDataBase.genreDataBase.GenreDataBaseService;
import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.infrastructure.mediaDataBase.MovieDataBaseRepository;
import org.epita.infrastructure.mediaDataBase.MovieDataBaseRepositoryImpl;
import org.epita.infrastructure.mediaDataBase.TmdbConfig;
import org.epita.infrastructure.mediaDataBase.apidto.MovieLightResponseDto;
import org.epita.infrastructure.mediaDataBase.apidto.SearchMoviesResponseDto;
import org.epita.infrastructure.mediaDataBase.mapper.GenreApiMapper;
import org.epita.infrastructure.mediaDataBase.mapper.MovieApiMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MovieDataBaseServiceImpl.class})
public class MovieDataBaseServiceTest {

    @Autowired
    MovieDataBaseService movieDataBaseService;

    @MockBean
    MovieDataBaseRepository movieDataBaseRepositoryMock;

    @MockBean
    GenreDataBaseService genreDataBaseServiceMock;

    @MockBean
    PreferencesUtilisateurService preferencesUtilisateurService;

    private MovieDataBase movie1;

    @BeforeEach
    public void setUp() {
        // définition des movieTmdb

        MovieDataBase movie1 = new MovieDataBase();
        movie1.setIdDataBase(8265L);
        movie1.setDateSortie(LocalDate.of(2008,2,20));
        movie1.setDuree(0);
        movie1.setNoteDataBase(6.673F);
        movie1.setTitre("Bienvenue chez les Ch'tis");
        movie1.setCheminAffichePaysage("/sBmLZreEkVK3bc6LLEyZFgKuxjk.jpg");
        movie1.setCheminAffichePortrait("/dfht1lGq2ALbrRkMj35dUrj5kHG.jpg");
        movie1.setResume("Philippe Abrams est directeur de la poste de Salon‐de‐Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d’obtenir une mutation sur la Côte d’Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord. Pour les Abrams, sudistes pleins de préjugés, le Nord c’est l’horreur, une région glacée, peuplée d’êtres rustres, éructant un langage incompréhensible, le «cheutimi». Philippe ira seul. À sa grande surprise, il découvre un endroit charmant, une équipe chaleureuse, des gens accueillants, et se fait un ami: Antoine, le facteur et le carillonneur du village, à la mère possessive et aux amours contrariées. Quand Philippe revient à Salon, Julie refuse de croire qu’il se plait dans le Nord. Elle pense même qu’il lui ment pour la ménager. Pour la satisfaire et se simplifier la vie, Philippe lui fait croire qu’en effet, il vit un enfer à Bergues.");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(35, "Comedie"));
        genresMovie.add(1, new GenreDataBase(18, "Drame"));
        genresMovie.add(2, new GenreDataBase(10749, "Romance"));
        movie1.setGenres(genresMovie);

        List<MovieDataBase> listMovies = new ArrayList<>();
        listMovies.add(movie1);

        when(movieDataBaseRepositoryMock.findDetailMovieDataBase(8265L)).thenReturn(movie1);

    }

    @Test
    public void rechercherDetailDuFilm_IdTmdb_8265() {

        // Given  @BeforeEach
        long Id = 8265;

        MovieDataBase movie1 = new MovieDataBase();
        movie1.setIdDataBase(8265L);
        movie1.setDateSortie(LocalDate.of(2008,2,20));
        movie1.setDuree(0);
        movie1.setNoteDataBase(6.673F);
        movie1.setTitre("Bienvenue chez les Ch'tis");
        movie1.setCheminAffichePaysage("/sBmLZreEkVK3bc6LLEyZFgKuxjk.jpg");
        movie1.setCheminAffichePortrait("/dfht1lGq2ALbrRkMj35dUrj5kHG.jpg");
        movie1.setResume("Philippe Abrams est directeur de la poste de Salon‐de‐Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d’obtenir une mutation sur la Côte d’Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord. Pour les Abrams, sudistes pleins de préjugés, le Nord c’est l’horreur, une région glacée, peuplée d’êtres rustres, éructant un langage incompréhensible, le «cheutimi». Philippe ira seul. À sa grande surprise, il découvre un endroit charmant, une équipe chaleureuse, des gens accueillants, et se fait un ami: Antoine, le facteur et le carillonneur du village, à la mère possessive et aux amours contrariées. Quand Philippe revient à Salon, Julie refuse de croire qu’il se plait dans le Nord. Elle pense même qu’il lui ment pour la ménager. Pour la satisfaire et se simplifier la vie, Philippe lui fait croire qu’en effet, il vit un enfer à Bergues.");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(35, "Comedie"));
        genresMovie.add(1, new GenreDataBase(18, "Drame"));
        genresMovie.add(2, new GenreDataBase(10749, "Romance"));
        movie1.setGenres(genresMovie);

        List<MovieDataBase> listMovies = new ArrayList<>();
        listMovies.add(movie1);

        when(movieDataBaseRepositoryMock.findDetailMovieDataBase(8265L)).thenReturn(movie1);

        // When
        final MovieDataBase filmTrouve = this.movieDataBaseService.findMovieById(Id);

        // Then
        //comparaison du titre du film trouvé
//        assertThat(filmTrouve).isEqualTo(movie1);
        assertThat(filmTrouve.getTitre()).isEqualTo(movie1.getTitre());
    }

//    @Test
//    public void rechercherLaListeDeTousLesFilms_contenant_caracteres_bienvenuechezleschtis() {
//        // Given
//        String query = "bienvenue%20chez%20les%20ch%27tis";
//        final MovieDataBase expected = this.movieDataBaseRepositoryMock.searchAllMovieDataBaseWithQuery(query);
//
//        // When
//        final List<MovieDataBase> trouves = this.movieDataBaseService.searchMovies(query);
//        System.out.println("trouves: " + trouves);
//
//        // Then
//        //le tableau des films trouvés est de 1
//        assertThat(trouves).hasSize(1);
//    }
//
//    @Test
//    public void rechercherLaListeDeTousLesFilms_contenant_caracteres_marsattack() {
//        // Given
//        String query = "mars%20attack";
//
//        // When
//        final List<MovieDataBase> trouves = this.movieDataBaseService.searchMovies(query);
//        System.out.println("trouves: " + trouves);
//
//        // Then
//        //le tableau des films trouvés est de 1
//        assertThat(trouves).hasSize(5);
//    }
//
//    @Test
//    public void rechercherLaListeDeTousLesFilms_contenant_caracteres_bienvenuechezleschtis_appel_API() {
//        // Given
//        String query = "bienvenue%20chez%20les%20ch%27tis";
//
//        SearchMoviesResponseDto movie1 = new SearchMoviesResponseDto();
//        movie1.setPage(1);
//
//        MovieLightResponseDto movieLightResponseDto = new MovieLightResponseDto();
//        movieLightResponseDto.setAdult(false);
//        movieLightResponseDto.setBackdrop_path("/sBmLZreEkVK3bc6LLEyZFgKuxjk.jpg");
//        List<Integer> genres = new ArrayList<>();
//        genres.add(35);
//        genres.add(18);
//        genres.add(10749);
//        movieLightResponseDto.setGenre_ids(genres);
//        movieLightResponseDto.setId(8265);
//        movieLightResponseDto.setOriginal_language("fr");
//        movieLightResponseDto.setOriginal_title("Bienvenue chez les Ch'tis");
//        movieLightResponseDto.setOverview("Philippe Abrams est directeur de la poste de Salon‐de‐Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d’obtenir une mutation sur la Côte d’Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord. Pour les Abrams, sudistes pleins de préjugés, le Nord c’est l’horreur, une région glacée, peuplée d’êtres rustres, éructant un langage incompréhensible, le «cheutimi». Philippe ira seul. À sa grande surprise, il découvre un endroit charmant, une équipe chaleureuse, des gens accueillants, et se fait un ami: Antoine, le facteur et le carillonneur du village, à la mère possessive et aux amours contrariées. Quand Philippe revient à Salon, Julie refuse de croire qu’il se plait dans le Nord. Elle pense même qu’il lui ment pour la ménager. Pour la satisfaire et se simplifier la vie, Philippe lui fait croire qu’en effet, il vit un enfer à Bergues.");
//        movieLightResponseDto.setPopularity(15.869f);
//        movieLightResponseDto.setPoster_path("/dfht1lGq2ALbrRkMj35dUrj5kHG.jpg");
//        movieLightResponseDto.setRelease_date("2008-02-20");
//        movieLightResponseDto.setTitle("Bienvenue chez les Ch'tis");
//        movieLightResponseDto.setVideo(false);
//        movieLightResponseDto.setVote_average(6.672f);
//        movieLightResponseDto.setVote_count(2264);
//
//        List<MovieLightResponseDto> results = new ArrayList<>();
//        results.add(movieLightResponseDto);
//        movie1.setResults(results);
//        movie1.setTotal_pages(1);
//        movie1.setTotal_results(1);
//
//        // When
//        final List<MovieDataBase> trouves = this.movieDataBaseService.searchMovies(query);
//        System.out.println("movie1: " + movie1);
//        System.out.println("trouves: " + trouves);
//
//        // Then
//        // comparaison de l'id du film trouvé en index 1
//        assertThat(trouves.get(0).getIdDataBase()).isEqualTo(movie1.getResults().get(0).getId());
//    }
//
//
//
//    @Test
//    public void rechercherSuggestionDeFilms_Pour_Page_1_appel_API() {
//        // Given
//        int page = 1;
//
//        // When
//        final List<MovieDataBase> trouves = this.movieDataBaseService.searchSuggestedMovies(page);
//
//        // Then
//
//        //le tableau des films trouvés est de 20
//        assertThat(trouves).hasSize(20);
//
//    }
}
