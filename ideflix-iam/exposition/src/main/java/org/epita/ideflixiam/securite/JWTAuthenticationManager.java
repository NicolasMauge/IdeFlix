package org.epita.ideflixiam.securite;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.epita.ideflixiam.application.exception.ErreurFormatLoginException;
import org.epita.ideflixiam.application.exception.IdeFlixIamException;
import org.epita.ideflixiam.application.exception.UtilisateurInexistantException;
import org.epita.ideflixiam.application.utilisateur.UtilisateurService;
import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.epita.ideflixiam.exposition.utilisateur.UtilisateurConvertisseur;
import org.epita.ideflixiam.exposition.utilisateur.UtilisateurEntreeDto;
import org.epita.ideflixiam.exposition.utilisateur.UtilisateurReponseLoginDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.epita.ideflixiam.common.ConstantesUtiles.EXPIRATION_SESSION_MINUTES;

public class JWTAuthenticationManager extends UsernamePasswordAuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(JWTAuthenticationManager.class);
    private final AuthenticationManager authenticationManager;
    UtilisateurConvertisseur utilisateurConvertisseur;
    private String SECRET_IAM;
    private UtilisateurService utilisateurService;

    Dechiffreur dechiffreur;

    public JWTAuthenticationManager(AuthenticationManager authenticationManager,
                                    String secretIam,
                                    UtilisateurService utilisateurService,
                                    UtilisateurConvertisseur utilisateurConvertisseur) {
        this.authenticationManager = authenticationManager;
        this.SECRET_IAM = secretIam;
        this.utilisateurService = utilisateurService;
        this.utilisateurConvertisseur = utilisateurConvertisseur;
        this.dechiffreur = new Dechiffreur(this.SECRET_IAM);
    }


    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {


        ObjectMapper mapper = new ObjectMapper();
        UtilisateurEntreeDto utilisateurDto = null;

        try {
            //Convertir le contenu du body de la requête en JSON
            // vers un objet java en utilisant Jackson
            utilisateurDto = mapper
                    .readValue(request.getInputStream(), UtilisateurEntreeDto.class);

            //logger.debug("IAM - attemptAuthentification : " + utilisateurDto.getEmail());

        } catch (IOException e) {
            throw new ErreurFormatLoginException("Echec de lecture du JSON fourni en entrée.");
        }

        return authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                utilisateurDto.getEmail(),
                                dechiffreur.dechiffrer(utilisateurDto.getMotDePasse(), utilisateurDto.getEmail())));
    }

    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException,
            ServletException {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach(au -> {
            roles.add(au.getAuthority());
        });

        OffsetDateTime odt = OffsetDateTime.now();
        ZoneOffset zoneOffset = odt.getOffset(); // On récupère le fuseau horaire pour calculer le moment d'expiration du token

        String jwt = JWT.create().withSubject(springUser.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withExpiresAt(LocalDateTime.now().plusMinutes(EXPIRATION_SESSION_MINUTES).toInstant(zoneOffset))
                .sign(Algorithm.HMAC256(this.SECRET_IAM));
        response.addHeader("Authorization", "Bearer " + jwt); // on passe par le body car on ne sait pas lire le header depuis Angular

        logger.debug("IAM - " + springUser.getUsername() + " connecté avec succès.");


        UtilisateurEntity utilisateur = null;
        try {
            utilisateur = utilisateurService.recupererUtilisateurParEmail(springUser.getUsername());
        } catch (UtilisateurInexistantException e) {
            throw new IdeFlixIamException("Utilisateur ou mot passe erroné");
        }

        UtilisateurReponseLoginDto utilisateurReponseLoginDto = utilisateurConvertisseur.convertirEntiteVersReponseLoginDto(utilisateur, jwt);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(utilisateurReponseLoginDto);

        response.getWriter()
                .println(json);

    }


}
