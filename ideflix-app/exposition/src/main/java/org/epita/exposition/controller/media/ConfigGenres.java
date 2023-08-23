package org.epita.exposition.controller.media;

import org.epita.application.media.genre.GenreService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class ConfigGenres {

    private GenreService genreService;

    public ConfigGenres(GenreService genreService) {
        this.genreService = genreService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshEvent() {
        genreService.loadGenres();
    }
}
