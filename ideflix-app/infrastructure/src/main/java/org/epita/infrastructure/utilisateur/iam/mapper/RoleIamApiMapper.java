package org.epita.infrastructure.utilisateur.iam.mapper;

import org.epita.domaine.utilisateuriam.RoleIamEntity;
import org.epita.infrastructure.utilisateur.iam.apidto.RoleIamApiDto;
import org.springframework.stereotype.Component;

@Component
public class RoleIamApiMapper {

    RoleIamEntity mapApiDtoToEntity(RoleIamApiDto roleIamApiDto) {
        return new RoleIamEntity(roleIamApiDto.getNomRole());
    }

}
