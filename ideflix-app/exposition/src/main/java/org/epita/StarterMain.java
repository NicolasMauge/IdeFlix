package org.epita;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterMain {

    public static final Logger logger = LoggerFactory.getLogger(StarterMain.class);

    public static void main(String[] args) {
        SpringApplication.run(StarterMain.class, args);

        logger.info("==========================================================");
        logger.info("= Démarrage Ideflix APP                                  =");
        logger.info("= http://localhost:8081/api/v1/swagger-ui/               =");
        logger.info("==========================================================");
    }
}