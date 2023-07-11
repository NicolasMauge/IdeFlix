package org.epita.ideflixiam.infrastructure;

import org.epita.ideflixiam.domaine.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
