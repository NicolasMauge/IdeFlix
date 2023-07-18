package org.epita.ideflixiam.application.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class EncodeurMotDePasse {

//
//    BCryptPasswordEncoder passwordEncoder;
//
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        if (this.passwordEncoder == null) {
//            this.passwordEncoder = new BCryptPasswordEncoder();
//        }
//        return this.passwordEncoder;
//    }


    public EncodeurMotDePasse() {
    }
}
