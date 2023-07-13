package org.epita.ideflixiam.application.role;

import org.epita.ideflixiam.domaine.RoleEntity;

public interface RoleService {

    RoleEntity recupererRoleParNomRole(String nomRole);

}
