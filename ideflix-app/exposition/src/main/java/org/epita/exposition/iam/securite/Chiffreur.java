package org.epita.exposition.iam.securite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

@Component
public class Chiffreur {

    private static final Logger logger = LoggerFactory.getLogger(Chiffreur.class);

    @Value("${org.epita.ideflixapp.secretiam}")
    public String SECRET_IAM;

    public Chiffreur() {
    }

    private String stringToHexa(String stringSource) {
        try {
            byte[] byteSource = stringSource.getBytes("UTF-8");
            return DatatypeConverter.printHexBinary(byteSource);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    public String chiffrer(String messageEnClair, String sel) {
        TextEncryptor cryptoMachine = Encryptors.text(stringToHexa(SECRET_IAM), stringToHexa(sel));

        return cryptoMachine.encrypt(messageEnClair);
    }

//    public String dechiffrer(String messageChiffre, String sel) {
//        // il faut le même sel que lors du chiffrement.
//        TextEncryptor cryptoMachine = Encryptors.text(stringToHexa(SECRET_IAM), stringToHexa(sel));
//
//        return cryptoMachine.decrypt(messageChiffre);
//    }

}
