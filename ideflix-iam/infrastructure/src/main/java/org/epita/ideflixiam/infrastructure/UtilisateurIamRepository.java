package org.epita.ideflixiam.infrastructure;

import org.epita.ideflixiam.domaine.UtilisateurIam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurIamRepository extends JpaRepository<UtilisateurIam, Long> {

    UtilisateurIam getUtilisateurIamByEmail(String email);

}
