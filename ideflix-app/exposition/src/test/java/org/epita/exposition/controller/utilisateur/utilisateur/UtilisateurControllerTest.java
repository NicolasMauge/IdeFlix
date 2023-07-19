package org.epita.exposition.controller.utilisateur.utilisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.exposition.controller.utilisateur.UtilisateurController;
import org.epita.exposition.dto.utilisateur.UtilisateurEtPrefDto;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.epita.exposition.mapper.utilisateur.UtilisateurEtPrefMapper;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UtilisateurMapper.class, UtilisateurController.class, UtilisateurEtPrefMapper.class, PreferencesUtilisateurMapper.class, GenreMapper.class})
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

    private void callServeur(String url, Object o) throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(asJsonString(o))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @BeforeAll
    public static void initMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void should_return_StatusOk_et_Up() throws Exception {
        this.mvc.perform(get("/utilisateur/health-check")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("UP")));
    }

    @Test
    public void creerUtilisateur_should_return_StatusCreated() throws Exception {
        LocalDate dateLocal = LocalDate.of(2023,7,13);
        PreferencesUtilisateurDto preferencesUtilisateurDto = new PreferencesUtilisateurDto(null, "pseudo", new ArrayList<>());

        callServeur("/utilisateur",
                new UtilisateurEtPrefDto(null, "test@test.com", "nom", "prenom", dateLocal, preferencesUtilisateurDto));
    }

    @Test
    public void creerPlusieursUtilisateurs_should_return_StatusCreated() throws Exception {
        LocalDate dateLocal = LocalDate.of(2023,7,13);
        PreferencesUtilisateurDto preferencesUtilisateurDto = new PreferencesUtilisateurDto(null, "pseudo", new ArrayList<>());
        UtilisateurEtPrefDto utilisateurEtPrefDto = new UtilisateurEtPrefDto(null, "test@test.com", "nom", "prenom", dateLocal, preferencesUtilisateurDto);

        LocalDate dateLocal2 = LocalDate.of(2023,7,13);
        PreferencesUtilisateurDto preferencesUtilisateurDto2 = new PreferencesUtilisateurDto(null, "pseudo 2", new ArrayList<>());
        UtilisateurEtPrefDto utilisateurEtPrefDto2 = new UtilisateurEtPrefDto(null, "test2@test.com", "nom2", "prenom2", dateLocal, preferencesUtilisateurDto);

        List<UtilisateurEtPrefDto> utilisateurEtPrefDtoList = new ArrayList<>();
        utilisateurEtPrefDtoList.add(utilisateurEtPrefDto);
        utilisateurEtPrefDtoList.add(utilisateurEtPrefDto2);

        callServeur("/utilisateur/masse",
                utilisateurEtPrefDtoList);
    }

}
