package org.epita.ideflixiam.infrastructure;

import org.epita.ideflixiam.domaine.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, String> {

    UtilisateurEntity findByEmail(String email);

}
