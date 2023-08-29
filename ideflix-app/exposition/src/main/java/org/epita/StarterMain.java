package org.epita;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe de démarrage de l'application Springboot.
 */

@SpringBootApplication
public class StarterMain {

    public static final Logger logger = LoggerFactory.getLogger(StarterMain.class);


    /**
     * Méthode pour le lancement de l'application.
     *
     * @param args Paramètres fournis au lancement (dans la ligne de commande). Les paramètres ne sont pas utilisés et sont ignorés.
     */
    public static void main(String[] args) {
        SpringApplication.run(StarterMain.class, args);

        logger.info("==========================================================");
        logger.info("= Démarrage Ideflix APP                                  =");
        logger.info("= http://localhost:8081/api/v1/swagger-ui.html           =");
        logger.info("==========================================================");

    }
}