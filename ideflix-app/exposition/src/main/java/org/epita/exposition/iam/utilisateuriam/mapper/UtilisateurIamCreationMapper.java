package org.epita.exposition.iam.utilisateuriam.mapper;

import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamCreationDto;

public class UtilisateurIamCreationMapper extends Mapper<UtilisateurIamEntity, UtilisateurIamCreationDto> {
    @Override
    public UtilisateurIamCreationDto mapEntityToDto(UtilisateurIamEntity input) {
        return null;
    }

    @Override
    public UtilisateurIamEntity mapDtoToEntity(UtilisateurIamCreationDto input) {
        return new UtilisateurIamEntity(


        );
    }
}
