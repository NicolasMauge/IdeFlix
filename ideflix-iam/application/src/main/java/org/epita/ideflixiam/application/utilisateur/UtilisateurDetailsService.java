package org.epita.ideflixiam.application.utilisateur;

import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.epita.ideflixiam.infrastructure.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurDetailsService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(UtilisateurDetailsService.class);

    UtilisateurRepository utilisateurRepository;

    public UtilisateurDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UtilisateurEntity utilisateur = utilisateurRepository.findByEmail(username);

        if (utilisateur == null) {
            logger.debug("IAM - utilisateur " + username + " introuvable.");

            // TODO
            return null;
        } else {

            List<GrantedAuthority> authorities = utilisateur
                    .getListeRoles()
                    .stream()
                    .map(role -> (GrantedAuthority) new SimpleGrantedAuthority(role.getNomRole()))
                    .toList();

            return new User(utilisateur.getEmail(), utilisateur.getMotDePasse(), authorities);
        }


    }
}
