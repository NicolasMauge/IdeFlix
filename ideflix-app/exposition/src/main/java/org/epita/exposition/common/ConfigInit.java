package org.epita.exposition.common;

import org.epita.application.iam.service.UtilisateurIamService;
import org.epita.application.media.genre.GenreService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class ConfigInit {

    private GenreService genreService;
    private final UtilisateurIamService utilisateurIamService;

    public ConfigInit(GenreService genreService, UtilisateurIamService utilisateurIamService) {
        this.genreService = genreService;
        this.utilisateurIamService = utilisateurIamService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshEvent() {
        
        genreService.loadGenres();
        utilisateurIamService.initIam();
    }
}
