package org.epita.infrastructure.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferencesUtilisateurRepository extends JpaRepository<PreferencesUtilisateurEntity, Long> {
}
