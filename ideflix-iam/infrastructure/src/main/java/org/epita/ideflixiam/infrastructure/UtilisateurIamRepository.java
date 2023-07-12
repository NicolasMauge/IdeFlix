package org.epita.ideflixiam.infrastructure;

import org.epita.ideflixiam.domaine.UtilisateurIamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurIamRepository extends JpaRepository<UtilisateurIamEntity, String> {

    UtilisateurIamEntity findByEmail(String email);

}
