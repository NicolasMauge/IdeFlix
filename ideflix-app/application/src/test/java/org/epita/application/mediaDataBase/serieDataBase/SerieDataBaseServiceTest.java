package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.application.mediaDataBase.genreDataBase.GenreDataBaseService;
import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.mediaDataBase.SerieDataBaseRepository;
import org.epita.infrastructure.utilisateur.PreferencesUtilisateurRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SerieDataBaseServiceImpl.class})
public class SerieDataBaseServiceTest {

    @Autowired
    SerieDataBaseService serieDataBaseService;

    @MockBean
    SerieDataBaseRepository serieDataBaseRepositoryMock;

    @MockBean
    PreferencesUtilisateurService preferencesUtilisateurService;

    @MockBean
    PreferencesUtilisateurRepository repositoryMock;

    @MockBean
    GenreDataBaseService genreDataBaseServiceMock;

    PreferencesUtilisateurEntity preferencesUtilisateur;

    UtilisateurEntity utilisateur;

    @BeforeEach
    public void setUp() {
        // définition de l'utilisateur
        utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        preferencesUtilisateur = new PreferencesUtilisateurEntity();
        preferencesUtilisateur.setId(1L);
        preferencesUtilisateur.setPseudo("pseudo 1");
        preferencesUtilisateur.setUtilisateur(this.utilisateur);

        List<GenreEntity> genreEntityList = new ArrayList<>();
        GenreEntity genre = new GenreEntity();
        genre.setId(1L);
        genre.setNomGenre("genre 1");
        genreEntityList.add(genre);

        GenreEntity genre2 = new GenreEntity();
        genre2.setId(2L);
        genre2.setNomGenre("genre 2");
        genreEntityList.add(genre2);

        preferencesUtilisateur.setGenreList(genreEntityList);

        preferencesUtilisateurService.creerPreferencesUtilisateur(preferencesUtilisateur);

        when(repositoryMock.findById(1L)).thenReturn(Optional.of(preferencesUtilisateur));
    }

    @Test
    public void rechercherDetailSerie_IdTmdb_1408() {

        SerieDataBase serie1 = new SerieDataBase();
        serie1.setIdDataBase(1408L);
        serie1.setDateSortie(LocalDate.of(2004, 11, 16));
        serie1.setDuree(0);
        serie1.setNoteDataBase(8.6F);
        serie1.setTitre("Dr House");
        serie1.setCheminAffichePaysage("/r0Q6eeN9L1ORL9QsV0Sg8ZV3vnv.jpg");
        serie1.setCheminAffichePortrait("/2Bw00vWztWFeTFpWTuPbUMkyDCA.jpg");
        serie1.setResume("Le docteur Gregory House, est un brillant médecin à tendance misanthrope qui dirige une équipe d'internistes au sein de l'hôpital fictif de Princeton-Plainsboro dans le New Jersey. House est un personnage arrogant, cynique, anticonformiste et asocial. Il souffre d'une claudication provenant d'une douleur chronique à la jambe droite due à un infarctus du muscle de la cuisse. Il marche avec une canne et abuse de Vicodin, un analgésique opiacé, pour soulager sa douleur");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(18, "Drame"));
        genresMovie.add(1, new GenreDataBase(9648, "Mystère"));
        serie1.setGenres(genresMovie);

        // Given
        long Id = 1408;

        when(serieDataBaseRepositoryMock.findDetailSerieDataBase(Id)).thenReturn(serie1);

        // When
        final SerieDataBase filmTrouve = this.serieDataBaseService.findSerieById(Id);

        // Then
        //comparaison du titre du film trouvé
//        assertThat(filmTrouve).isEqualTo(movie1);
        assertThat(filmTrouve.getTitre()).isEqualTo(serie1.getTitre());
    }


    @Test
    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_bienvenuechezleschtis() {

        SerieDataBase serie1 = new SerieDataBase();
        serie1.setIdDataBase(1408L);
        serie1.setDateSortie(LocalDate.of(2004, 11, 16));
        serie1.setDuree(0);
        serie1.setNoteDataBase(8.6F);
        serie1.setTitre("Dr House");
        serie1.setCheminAffichePaysage("/r0Q6eeN9L1ORL9QsV0Sg8ZV3vnv.jpg");
        serie1.setCheminAffichePortrait("/2Bw00vWztWFeTFpWTuPbUMkyDCA.jpg");
        serie1.setResume("Le docteur Gregory House, est un brillant médecin à tendance misanthrope qui dirige une équipe d'internistes au sein de l'hôpital fictif de Princeton-Plainsboro dans le New Jersey. House est un personnage arrogant, cynique, anticonformiste et asocial. Il souffre d'une claudication provenant d'une douleur chronique à la jambe droite due à un infarctus du muscle de la cuisse. Il marche avec une canne et abuse de Vicodin, un analgésique opiacé, pour soulager sa douleur");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(18, "Drame"));
        genresMovie.add(1, new GenreDataBase(9648, "Mystère"));
        serie1.setGenres(genresMovie);

        List<SerieDataBase> listSeries = new ArrayList<>();
        listSeries.add(serie1);

        // Given
        String query = "Dr%House";

        when(this.serieDataBaseRepositoryMock.searchSeriesDataBase(query)).thenReturn(listSeries);

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);

        // Then
        //le tableau des films trouvés est de 1
        assertThat(trouves).hasSize(1);
    }

//    @Test
//    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_iletaitunefois() {
//        // Given
//        String query = "il%20%C3%A9tait%20une%20fois";
//
//        // When
//        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);
//        System.out.println("trouves: " + trouves);
//
//        // Then
//        //le tableau des films trouvés est de 20 (2 pages en réalité et 38 results au total)
//        assertThat(trouves).hasSize(20);
//    }
//
//    @Test
//    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_drHouse() {
//        // Given
//        String query = "dr%20house";
//
//        // When
//        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);
//        System.out.println("trouves: " + trouves);
//
//        // Then
//        //le tableau des films trouvés est de 4
//        assertThat(trouves).hasSize(4);
//    }

    @Test
    public void rechercherSuggestionDeSeries_Pour_Page_1_appel_API() {

        SerieDataBase serie1 = new SerieDataBase();
        serie1.setIdDataBase(1408L);
        serie1.setDateSortie(LocalDate.of(2004, 11, 16));
        serie1.setDuree(0);
        serie1.setNoteDataBase(8.6F);
        serie1.setTitre("Dr House");
        serie1.setCheminAffichePaysage("/r0Q6eeN9L1ORL9QsV0Sg8ZV3vnv.jpg");
        serie1.setCheminAffichePortrait("/2Bw00vWztWFeTFpWTuPbUMkyDCA.jpg");
        serie1.setResume("Le docteur Gregory House, est un brillant médecin à tendance misanthrope qui dirige une équipe d'internistes au sein de l'hôpital fictif de Princeton-Plainsboro dans le New Jersey. House est un personnage arrogant, cynique, anticonformiste et asocial. Il souffre d'une claudication provenant d'une douleur chronique à la jambe droite due à un infarctus du muscle de la cuisse. Il marche avec une canne et abuse de Vicodin, un analgésique opiacé, pour soulager sa douleur");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(18, "Drame"));
        genresMovie.add(1, new GenreDataBase(9648, "Mystère"));
        serie1.setGenres(genresMovie);

        List<SerieDataBase> listSeries = new ArrayList<>();
        listSeries.add(serie1);

        // Given
        int page = 1;

        when(serieDataBaseRepositoryMock.searchSuggestedSerieDataBase(page)).thenReturn(listSeries);

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSuggestedSeries(page);

        // Then

        //le tableau des films trouvés est de 1
        assertThat(trouves).hasSize(1);
    }

    @Test
    public void rechercherSuggestionDeSeries_Pour_Page_1_et_Pour_Utilisateur() {
        // Given
        int page = 1;
        String email = "test@test.com";

        //Given
        // définition de l'utilisateur
        utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        preferencesUtilisateur = new PreferencesUtilisateurEntity();
        preferencesUtilisateur.setId(1L);
        preferencesUtilisateur.setPseudo("pseudo 1");
        preferencesUtilisateur.setUtilisateur(this.utilisateur);

        List<GenreEntity> genreEntityList = new ArrayList<>();
        GenreEntity genre = new GenreEntity();
        genre.setId(1L);
        genre.setIdTmdb("18");
        genre.setNomGenre("genre 1");
        genreEntityList.add(genre);

        GenreEntity genre2 = new GenreEntity();
        genre2.setId(2L);
        genre2.setIdTmdb("40");
        genre2.setNomGenre("genre 2");
        genreEntityList.add(genre2);

        preferencesUtilisateur.setGenreList(genreEntityList);

        System.out.println("pref utilisateur: " + preferencesUtilisateur.getUtilisateur().getEmail());

        SerieDataBase serie1 = new SerieDataBase();
        serie1.setIdDataBase(1408L);
        serie1.setDateSortie(LocalDate.of(2004, 11, 16));
        serie1.setDuree(0);
        serie1.setNoteDataBase(8.6F);
        serie1.setTitre("Dr House");
        serie1.setCheminAffichePaysage("/r0Q6eeN9L1ORL9QsV0Sg8ZV3vnv.jpg");
        serie1.setCheminAffichePortrait("/2Bw00vWztWFeTFpWTuPbUMkyDCA.jpg");
        serie1.setResume("Le docteur Gregory House, est un brillant médecin à tendance misanthrope qui dirige une équipe d'internistes au sein de l'hôpital fictif de Princeton-Plainsboro dans le New Jersey. House est un personnage arrogant, cynique, anticonformiste et asocial. Il souffre d'une claudication provenant d'une douleur chronique à la jambe droite due à un infarctus du muscle de la cuisse. Il marche avec une canne et abuse de Vicodin, un analgésique opiacé, pour soulager sa douleur");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(18, "Drame"));
        genresMovie.add(1, new GenreDataBase(9648, "Mystère"));
        serie1.setGenres(genresMovie);

        List<SerieDataBase> listSeries = new ArrayList<>();
        listSeries.add(serie1);

        when(this.preferencesUtilisateurService.trouverPreferenceUtilisateurParEmailUtilisateur(email)).thenReturn(preferencesUtilisateur);
        when(this.serieDataBaseRepositoryMock.searchSuggestedSerieDataBase(page)).thenReturn(listSeries);

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSuggestedSeriesSelonPreferences(email, page);

        // Then

        //le tableau des films trouvés est de 1
        assertThat(trouves).hasSize(1);
    }

    @Test
    public void rechercherSuggestionDeSeries_Pour_Page_1_SElon_preferences_Sans_Suggestions() {
        // Given
        int page = 1;
        String email = "test@test.com";

        //Given
        // définition de l'utilisateur
        utilisateur = new UtilisateurEntity();
        utilisateur.setId(1L);
        utilisateur.setEmail("test@test.com");

        preferencesUtilisateur = new PreferencesUtilisateurEntity();
        preferencesUtilisateur.setId(1L);
        preferencesUtilisateur.setPseudo("pseudo 1");
        preferencesUtilisateur.setUtilisateur(this.utilisateur);

        List<GenreEntity> genreEntityList = new ArrayList<>();
        GenreEntity genre = new GenreEntity();
        genre.setId(1L);
        genre.setIdTmdb("1");
        genre.setNomGenre("genre 1");
        genreEntityList.add(genre);

        GenreEntity genre2 = new GenreEntity();
        genre2.setId(2L);
        genre2.setIdTmdb("2");
        genre2.setNomGenre("genre 2");
        genreEntityList.add(genre2);

        preferencesUtilisateur.setGenreList(genreEntityList);

        System.out.println("pref utilisateur: " + preferencesUtilisateur.getUtilisateur().getEmail());

        SerieDataBase serie1 = new SerieDataBase();
        serie1.setIdDataBase(1408L);
        serie1.setDateSortie(LocalDate.of(2004, 11, 16));
        serie1.setDuree(0);
        serie1.setNoteDataBase(8.6F);
        serie1.setTitre("Dr House");
        serie1.setCheminAffichePaysage("/r0Q6eeN9L1ORL9QsV0Sg8ZV3vnv.jpg");
        serie1.setCheminAffichePortrait("/2Bw00vWztWFeTFpWTuPbUMkyDCA.jpg");
        serie1.setResume("Le docteur Gregory House, est un brillant médecin à tendance misanthrope qui dirige une équipe d'internistes au sein de l'hôpital fictif de Princeton-Plainsboro dans le New Jersey. House est un personnage arrogant, cynique, anticonformiste et asocial. Il souffre d'une claudication provenant d'une douleur chronique à la jambe droite due à un infarctus du muscle de la cuisse. Il marche avec une canne et abuse de Vicodin, un analgésique opiacé, pour soulager sa douleur");
        List<GenreDataBase> genresMovie = new ArrayList<>();
        genresMovie.add(0, new GenreDataBase(18, "Drame"));
        genresMovie.add(1, new GenreDataBase(9648, "Mystère"));
        serie1.setGenres(genresMovie);

        List<SerieDataBase> listSeries = new ArrayList<>();
        listSeries.add(serie1);

        when(this.preferencesUtilisateurService.trouverPreferenceUtilisateurParEmailUtilisateur(email)).thenReturn(preferencesUtilisateur);
        when(this.serieDataBaseRepositoryMock.searchSuggestedSerieDataBase(page)).thenReturn(listSeries);

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSuggestedSeriesSelonPreferences(email, page);

        // Then

        //le tableau des films trouvés est de 0
        assertThat(trouves).hasSize(0);
    }
}
