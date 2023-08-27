package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.application.utilisateur.preferences.PreferencesUtilisateurServiceImpl;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.mediaDataBase.GenreDataBase;
import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.mediaDataBase.SerieDataBaseRepositoryImpl;
import org.epita.infrastructure.mediaDataBase.TmdbConfig;
import org.epita.infrastructure.mediaDataBase.mapper.SerieApiMapper;
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
@SpringBootTest(classes = {SerieDataBaseServiceImpl.class,
                        SerieDataBaseRepositoryImpl.class,
                        TmdbConfig.class,
                        SerieApiMapper.class,
                        PreferencesUtilisateurServiceImpl.class})
public class SerieDataBaseServiceTest {

    @Autowired
    SerieDataBaseService serieDataBaseService;

    @Autowired
    PreferencesUtilisateurService preferencesUtilisateurService;

    @MockBean
    PreferencesUtilisateurRepository repositoryMock;

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
    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_bienvenuechezleschtis() {
        // Given
        String query = "bienvenue%20chez%20les%20ch%27tis";

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);
        System.out.println("trouves: " + trouves);

        // Then
        //le tableau des films trouvés est de 1
        assertThat(trouves).hasSize(0);
    }

    @Test
    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_iletaitunefois() {
        // Given
        String query = "il%20%C3%A9tait%20une%20fois";

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);
        System.out.println("trouves: " + trouves);

        // Then
        //le tableau des films trouvés est de 20 (2 pages en réalité et 38 results au total)
        assertThat(trouves).hasSize(20);
    }

    @Test
    public void rechercherLaListeDeToutesLesSeries_contenant_caracteres_drHouse() {
        // Given
        String query = "dr%20house";

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSeries(query);
        System.out.println("trouves: " + trouves);

        // Then
        //le tableau des films trouvés est de 4
        assertThat(trouves).hasSize(4);
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
        System.out.println("serie1: " + serie1);

        // Given
        long Id = 1408;

        // When
        final SerieDataBase filmTrouve = this.serieDataBaseService.findSerieById(Id);

        // Then
        //comparaison du titre du film trouvé
//        assertThat(filmTrouve).isEqualTo(movie1);
        assertThat(filmTrouve.getTitre()).isEqualTo(serie1.getTitre());
    }

    @Test
    public void rechercherSuggestionDeSeries_Pour_Page_1_appel_API() {
        // Given
        int page = 1;

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSuggestedSeries(page);

        // Then

        //le tableau des films trouvés est de 20
        assertThat(trouves).hasSize(20);
    }

    @Test
    public void rechercherSuggestionDeSeries_Pour_Page_1_et_Pour_Utilisateur_appel_API() {
        // Given
        int page = 1;
        String email = "caro@gmail.com";

        // When
        final List<SerieDataBase> trouves = this.serieDataBaseService.searchSuggestedSeriesSelonPreferences(email, page);
        System.out.println("trouves: " + trouves);

        // Then

        //le tableau des films trouvés est de 20
        assertThat(trouves).hasSize(20);
    }
}
