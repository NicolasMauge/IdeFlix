package org.epita.exposition.common;

import org.epita.application.media.genre.GenreService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class ConfigInit {

    private GenreService genreService;

    public ConfigInit(GenreService genreService) {
        this.genreService = genreService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshEvent() {
        genreService.loadGenres();
    }
}
