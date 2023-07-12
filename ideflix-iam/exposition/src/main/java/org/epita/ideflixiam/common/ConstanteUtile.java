package org.epita.ideflixiam.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConstanteUtile {

    @Value("${LE_SECRET_DES_SIGNATURES_IAM}")
    public static String SECRET_IAM;

}
