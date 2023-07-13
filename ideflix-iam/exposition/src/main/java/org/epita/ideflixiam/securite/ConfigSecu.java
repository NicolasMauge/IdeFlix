package org.epita.ideflixiam.securite;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSecu {

    @Value("${org.epita.ideflixiam.secretiam}")
    public String SECRET_IAM;

    public ConfigSecu() {
    }

    public String getSECRET_IAM() {
        return SECRET_IAM;
    }

    public void setSECRET_IAM(String SECRET_IAM) {
        this.SECRET_IAM = SECRET_IAM;
    }
}
