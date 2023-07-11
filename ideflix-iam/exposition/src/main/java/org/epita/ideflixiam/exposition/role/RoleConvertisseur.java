package org.epita.ideflixiam.exposition.role;

import org.epita.ideflixiam.domaine.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConvertisseur {

    public RoleDto convertirRoleVersRoleDto(Role role) {
        return new RoleDto(role.getNomRole());
    }

    public Role convertirRoleDtoVersRole(RoleDto roleDto) {
        return new Role(roleDto.getNomRole());
    }
}
