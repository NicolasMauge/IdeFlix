package org.epita.ideflixiam.etat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etat")
public class EtatApplication {

    @GetMapping
    String getEtatApplication() {
        return "IAM - IdeFlix IAM est en service. ";
    }


}
