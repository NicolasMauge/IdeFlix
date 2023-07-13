package org.epita.ideflixiam.exposition.role;

import org.epita.ideflixiam.domaine.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConvertisseur {

    public RoleDto convertirRoleVersRoleDto(RoleEntity roleEntity) {
        return new RoleDto(roleEntity.getNomRole());
    }

    public RoleEntity convertirRoleDtoVersRole(RoleDto roleDto) {
        return new RoleEntity(roleDto.getNomRole());
    }
}
