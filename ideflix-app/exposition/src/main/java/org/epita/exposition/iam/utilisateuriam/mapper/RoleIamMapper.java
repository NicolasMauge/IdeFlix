package org.epita.exposition.iam.utilisateuriam.mapper;

import org.epita.domaine.utilisateuriam.RoleIamEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.iam.utilisateuriam.dto.RoleIamDto;
import org.springframework.stereotype.Component;

@Component
public class RoleIamMapper extends Mapper<RoleIamEntity, RoleIamDto> {
    @Override
    public RoleIamDto mapEntityToDto(RoleIamEntity input) {
        return new RoleIamDto(input.getNomRole());
    }

    @Override
    public RoleIamEntity mapDtoToEntity(RoleIamDto input) {
        return new RoleIamEntity(input.getNomRole());
    }
}
