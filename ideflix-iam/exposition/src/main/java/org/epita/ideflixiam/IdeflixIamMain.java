package org.epita.ideflixiam;

import org.epita.ideflixiam.exposition.utilisateuriam.UtilisateurIamController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdeflixIamMain {

    static Logger logger = LoggerFactory.getLogger(UtilisateurIamController.class);

    public static void main(String[] args) {

        SpringApplication.run(IdeflixIamMain.class, args);

        logger.info("========================================================================================");
        logger.info(" Démarrage Ideflix IAM");
        logger.info(" http://localhost:8080/swagger-ui/");
        logger.info("========================================================================================");


    }
}
