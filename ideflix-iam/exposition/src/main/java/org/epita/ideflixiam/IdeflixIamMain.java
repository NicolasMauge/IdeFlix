package org.epita.ideflixiam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdeflixIamMain {

    public static void main(String[] args) {

        SpringApplication.run(IdeflixIamMain.class, args);

        System.out.println("========================================================================================");
        System.out.println(" Démarrage Ideflix IAM");
        System.out.println("http://localhost:8080/swagger-ui/");
        System.out.println("========================================================================================");
        System.out.println("");


    }
}
