package org.epita.exposition.controller.utilisateur.utilisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.exposition.media.genre.GenreMapper;
import org.epita.exposition.utilisateur.preferences.PreferencesUtilisateurDto;
import org.epita.exposition.utilisateur.preferences.PreferencesUtilisateurMapper;
import org.epita.exposition.utilisateur.utilisateur.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
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

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.when;
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

    @Test
    public void should_return_StatusOk_et_Up() throws Exception {
        this.mvc.perform(get("/utilisateur/health-check")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("UP")));
    }

    @Test
    public void should_return_StatusCreated() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        LocalDate dateLocal = LocalDate.of(2023,7,13);
        PreferencesUtilisateurDto preferencesUtilisateurDto = new PreferencesUtilisateurDto(null, "pseudo", new ArrayList<>());

        mvc.perform(MockMvcRequestBuilders
                        .post("/utilisateur")
                        .content(asJsonString(new UtilisateurEtPrefDto(null, "test@test.com", "nom", "prenom", dateLocal, preferencesUtilisateurDto)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
