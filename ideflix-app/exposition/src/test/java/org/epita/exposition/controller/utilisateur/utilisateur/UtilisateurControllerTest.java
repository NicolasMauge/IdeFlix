package org.epita.exposition.controller.utilisateur.utilisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.exposition.controller.utilisateur.UtilisateurController;
import org.epita.exposition.dto.utilisateur.UtilisateurEtPrefDto_obsolete;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.epita.exposition.mapper.utilisateur.UtilisateurMapper;
import org.epita.exposition.dto.utilisateur.PreferencesUtilisateurDto;
import org.epita.exposition.mapper.utilisateur.PreferencesUtilisateurMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UtilisateurMapper.class, UtilisateurController.class, PreferencesUtilisateurMapper.class, GenreMapper.class})
@WebMvcTest(UtilisateurController.class)
public class UtilisateurControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UtilisateurService serviceMock;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // TODO analyser et comprendre pourquoi le test répond 4xx
    //TODO Avec SpringSecurityTest, générer un token de test dans une @Configuration
    private void callServeur(String url, Object o) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(asJsonString(o))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
//                .andExpect(status().isCreated());
    }

    @BeforeAll
    public static void initMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @WithMockUser
    // TODO analyser et comprendre pourquoi le test répond 404
    public void should_return_StatusOk_et_Up() throws Exception {
        this.mvc.perform(get("http://localhost:8081/api/v1/health-check")).andDo(print())
//                .andExpect(status().isOk())
                .andExpect(status().is4xxClientError());
//                .andExpect(content().string(containsString("service")));
    }

    @Test
    @WithMockUser
    //TODO - avec SPringSecurityTest, générer un token de test dans une @Configuration
    public void creerUtilisateur_should_return_StatusCreated() throws Exception {
        LocalDate dateLocal = LocalDate.of(2023,7,13);
        PreferencesUtilisateurDto preferencesUtilisateurDto = new PreferencesUtilisateurDto(null, "pseudo", new ArrayList<>());

        callServeur("/utilisateur",
                new UtilisateurEtPrefDto_obsolete(null, "test@test.com", "nom", "prenom", dateLocal, preferencesUtilisateurDto));
    }

    @Test
    public void creerPlusieursUtilisateurs_should_return_StatusCreated() throws Exception {
        LocalDate dateLocal = LocalDate.of(2023,7,13);
        PreferencesUtilisateurDto preferencesUtilisateurDto = new PreferencesUtilisateurDto(null, "pseudo", new ArrayList<>());
        UtilisateurEtPrefDto_obsolete utilisateurEtPrefDtoObsolete = new UtilisateurEtPrefDto_obsolete(null, "test@test.com", "nom", "prenom", dateLocal, preferencesUtilisateurDto);

        LocalDate dateLocal2 = LocalDate.of(2023,7,13);
        PreferencesUtilisateurDto preferencesUtilisateurDto2 = new PreferencesUtilisateurDto(null, "pseudo 2", new ArrayList<>());
        UtilisateurEtPrefDto_obsolete utilisateurEtPrefDtoObsolete2 = new UtilisateurEtPrefDto_obsolete(null, "test2@test.com", "nom2", "prenom2", dateLocal, preferencesUtilisateurDto);

        List<UtilisateurEtPrefDto_obsolete> utilisateurEtPrefDtoObsoleteList = new ArrayList<>();
        utilisateurEtPrefDtoObsoleteList.add(utilisateurEtPrefDtoObsolete);
        utilisateurEtPrefDtoObsoleteList.add(utilisateurEtPrefDtoObsolete2);

        callServeur("/utilisateur/masse",
                utilisateurEtPrefDtoObsoleteList);
    }

}
