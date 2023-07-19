package org.epita.exposition.mapper.media.film;

import org.epita.domaine.media.FilmEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.FilmDto;
import org.epita.exposition.dto.media.MediaDto;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {FilmToMediaMapper.class})
@ContextConfiguration(classes = {GenreMapper.class})
public class FilmToMediaMapperTest {
    @Autowired
    private Mapper<FilmEntity, MediaDto> mapper;

    @Test
    public void should_return_mapEntityToDto() {
        // Given

        /*
        // Film
        FilmEntity film = new FilmEntity(

        )*/
    }

}
