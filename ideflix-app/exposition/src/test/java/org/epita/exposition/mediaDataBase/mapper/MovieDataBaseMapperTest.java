package org.epita.exposition.mediaDataBase.mapper;

import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.MovieDataBase;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.mediaDataBase.dto.GenreDataBaseResponseDto;
import org.epita.exposition.mediaDataBase.dto.MediaDataBaseResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MovieDataBaseMapper.class})
@ContextConfiguration(classes = {GenreDataBaseMapper.class})
public class MovieDataBaseMapperTest {

    @Autowired
    private Mapper<GenreDataBase, GenreDataBaseResponseDto> mapper;

    @Autowired
    private MovieDataBaseMapper mediaDataBaseMapper;

    @Test
    public void should_return_mapEntityToDto() {
        //Given

        MovieDataBase movie = new MovieDataBase();

        movie.setIdDataBase(8265L);
        movie.setDateSortie(LocalDate.of(2008, 2,20));
        movie.setDuree(0);
        movie.setNoteDataBase(6.673F);
        movie.setTitre("Bienvenue chez les Ch'tis");
        movie.setCheminAffichePaysage("/sBmLZreEkVK3bc6LLEyZFgKuxjk.jpg");
        movie.setCheminAffichePortrait("/dfht1lGq2ALbrRkMj35dUrj5kHG.jpg");
        movie.setResume("Philippe Abrams est directeur de la poste de Salon‐de‐Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d’obtenir une mutation sur la Côte d’Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord. Pour les Abrams, sudistes pleins de préjugés, le Nord c’est l’horreur, une région glacée, peuplée d’êtres rustres, éructant un langage incompréhensible, le «cheutimi». Philippe ira seul. À sa grande surprise, il découvre un endroit charmant, une équipe chaleureuse, des gens accueillants, et se fait un ami: Antoine, le facteur et le carillonneur du village, à la mère possessive et aux amours contrariées. Quand Philippe revient à Salon, Julie refuse de croire qu’il se plait dans le Nord. Elle pense même qu’il lui ment pour la ménager. Pour la satisfaire et se simplifier la vie, Philippe lui fait croire qu’en effet, il vit un enfer à Bergues.");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(35, "Comedie"));
        genresMovie.add(1, new GenreDataBase(18, "Drame"));
        genresMovie.add(2, new GenreDataBase(10749, "Romance"));
        movie.setGenres(genresMovie);

        //when
        MediaDataBaseResponseDto mediaDto = this.mediaDataBaseMapper.mapEntityToDto(movie);

        System.out.println("movie: " + movie.getIdDataBase());
        System.out.println("mediaDto: " + mediaDto.getIdDataBase());

        //then
        assertThat(mediaDto.getIdDataBase()).isEqualTo(movie.getIdDataBase());
        assertThat(mediaDto.getTitre()).isEqualTo(movie.getTitre());
        assertThat(mediaDto.getDateSortie()).isEqualTo(movie.getDateSortie().toString()); // Assuming dateSortie is LocalDate
        assertThat(mediaDto.getDuree()).isEqualTo(movie.getDuree());
        assertThat(mediaDto.getResume()).isEqualTo(movie.getResume());
        assertThat(mediaDto.getCheminAffichePortrait()).isEqualTo(movie.getCheminAffichePortrait());
        assertThat(mediaDto.getCheminAffichePaysage()).isEqualTo(movie.getCheminAffichePaysage());
        assertThat(mediaDto.getNoteDataBase()).isEqualTo(movie.getNoteDataBase());

        // Assertion pour les genres
        assertThat(mediaDto.getGenreDataBaseResponseDtos())
                .extracting("idDataBase", "nomGenre")
                .containsExactly(
                        tuple(35, "Comedie"),
                        tuple(18, "Drame"),
                        tuple(10749, "Romance")
                );

    }

    @Test
    public void should_return_mapListEntityToDto() {
        // Given

        MovieDataBase movie = new MovieDataBase();

        movie.setIdDataBase(8265L);
        movie.setDateSortie(LocalDate.of(2008, 2,20));
        movie.setDuree(0);
        movie.setNoteDataBase(6.673F);
        movie.setTitre("Bienvenue chez les Ch'tis");
        movie.setCheminAffichePaysage("/sBmLZreEkVK3bc6LLEyZFgKuxjk.jpg");
        movie.setCheminAffichePortrait("/dfht1lGq2ALbrRkMj35dUrj5kHG.jpg");
        movie.setResume("Philippe Abrams est directeur de la poste de Salon‐de‐Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d’obtenir une mutation sur la Côte d’Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord. Pour les Abrams, sudistes pleins de préjugés, le Nord c’est l’horreur, une région glacée, peuplée d’êtres rustres, éructant un langage incompréhensible, le «cheutimi». Philippe ira seul. À sa grande surprise, il découvre un endroit charmant, une équipe chaleureuse, des gens accueillants, et se fait un ami: Antoine, le facteur et le carillonneur du village, à la mère possessive et aux amours contrariées. Quand Philippe revient à Salon, Julie refuse de croire qu’il se plait dans le Nord. Elle pense même qu’il lui ment pour la ménager. Pour la satisfaire et se simplifier la vie, Philippe lui fait croire qu’en effet, il vit un enfer à Bergues.");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(35, "Comedie"));
        genresMovie.add(1, new GenreDataBase(18, "Drame"));
        genresMovie.add(2, new GenreDataBase(10749, "Romance"));
        movie.setGenres(genresMovie);

        MovieDataBase movie2 = new MovieDataBase();

        movie2.setIdDataBase(8265L);
        movie2.setDateSortie(LocalDate.of(2008, 2,20));
        movie2.setDuree(0);
        movie2.setNoteDataBase(6.673F);
        movie2.setTitre("Bienvenue chez les Ch'tis");
        movie2.setCheminAffichePaysage("/sBmLZreEkVK3bc6LLEyZFgKuxjk.jpg");
        movie2.setCheminAffichePortrait("/dfht1lGq2ALbrRkMj35dUrj5kHG.jpg");
        movie2.setResume("Philippe Abrams est directeur de la poste de Salon‐de‐Provence. Il est marié à Julie, dont le caractère dépressif lui rend la vie impossible. Pour lui faire plaisir, Philippe fraude afin d’obtenir une mutation sur la Côte d’Azur. Mais il est démasqué: il sera muté à Bergues, petite ville du Nord. Pour les Abrams, sudistes pleins de préjugés, le Nord c’est l’horreur, une région glacée, peuplée d’êtres rustres, éructant un langage incompréhensible, le «cheutimi». Philippe ira seul. À sa grande surprise, il découvre un endroit charmant, une équipe chaleureuse, des gens accueillants, et se fait un ami: Antoine, le facteur et le carillonneur du village, à la mère possessive et aux amours contrariées. Quand Philippe revient à Salon, Julie refuse de croire qu’il se plait dans le Nord. Elle pense même qu’il lui ment pour la ménager. Pour la satisfaire et se simplifier la vie, Philippe lui fait croire qu’en effet, il vit un enfer à Bergues.");
        List<GenreDataBase> genresMovie2 = new ArrayList<>();
        genresMovie2.add(0, new GenreDataBase(35, "Comedie"));
        genresMovie2.add(1, new GenreDataBase(18, "Drame"));
        genresMovie2.add(2, new GenreDataBase(10749, "Romance"));
        movie2.setGenres(genresMovie2);

        List<MovieDataBase> listMovies = new ArrayList<>();
        listMovies.add(movie);
        listMovies.add(movie2);

        System.out.println("listMovies: " + listMovies.size());

        // When
        List<MediaDataBaseResponseDto> listMediaDto = this.mediaDataBaseMapper.mapListEntityToDto(listMovies);

        // Then
        assertThat(listMediaDto).hasSize(2); // Vérifiez la taille de la liste

        MediaDataBaseResponseDto mediaDto = listMediaDto.get(0); // Obtenez le premier élément de la liste
        assertThat(mediaDto.getIdDataBase()).isEqualTo(movie.getIdDataBase());
        assertThat(mediaDto.getTitre()).isEqualTo(movie.getTitre());
        assertThat(mediaDto.getDateSortie()).isEqualTo(movie.getDateSortie().toString()); // Assuming dateSortie is LocalDate
        assertThat(mediaDto.getDuree()).isEqualTo(movie.getDuree());
        assertThat(mediaDto.getResume()).isEqualTo(movie.getResume());
        assertThat(mediaDto.getCheminAffichePortrait()).isEqualTo(movie.getCheminAffichePortrait());
        assertThat(mediaDto.getCheminAffichePaysage()).isEqualTo(movie.getCheminAffichePaysage());
        assertThat(mediaDto.getNoteDataBase()).isEqualTo(movie.getNoteDataBase());

        // Assertion pour les genres
        assertThat(mediaDto.getGenreDataBaseResponseDtos())
                .extracting("idDataBase", "nomGenre")
                .containsExactly(
                        tuple(35, "Comedie"),
                        tuple(18, "Drame"),
                        tuple(10749, "Romance")
                );

    }
}
