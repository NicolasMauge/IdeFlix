package org.epita.exposition.mediaDataBase.controller;

import org.epita.application.mediaDataBase.movieDataBase.MovieDataBaseService;
import org.epita.exposition.mediaDataBase.mapper.MovieDataBaseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MovieDataBaseService.class, MovieDataBaseMapper.class})
@WebMvcTest(MovieDataBaseController.class)
public class MovieDataBaseControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieDataBaseService movieDataBaseService;

    /*
    @Test
    public void rechercher2Medias_EmptyList_ReturnsEmptyResponse() throws Exception {
        // Configurez le comportement simulé de movieDataBaseService
        Mockito.when(movieDataBaseService.searchMovies(Mockito.anyString()))
                .thenReturn(Collections.emptyList());

        // Arrange
        String query = "bienvenue%20chez%20les%20ch%27tis";

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/MovieDataBase/recherche2/{query}", query))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }*/

}
