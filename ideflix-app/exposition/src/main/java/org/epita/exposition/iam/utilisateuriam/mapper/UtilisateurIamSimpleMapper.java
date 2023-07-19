package org.epita.exposition.iam.utilisateuriam.mapper;


import org.epita.domaine.utilisateuriam.UtilisateurIamEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.iam.utilisateuriam.dto.UtilisateurIamSimpleDto;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UtilisateurIamSimpleMapper extends Mapper<UtilisateurIamEntity, UtilisateurIamSimpleDto> {

//    private RoleIamMapper roleIamMapper;
//
//    public UtilisateurIamMapper(RoleIamMapper roleIamMapper) {
//        this.roleIamMapper = roleIamMapper;
//    }

    @Override
    public UtilisateurIamSimpleDto mapEntityToDto(UtilisateurIamEntity utilisateurIamEntity) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dateCreation = utilisateurIamEntity.getDateCreation().format(dateTimeFormatter);

        return new UtilisateurIamSimpleDto(
                utilisateurIamEntity.getIdUtilisateur(),
                utilisateurIamEntity.getNom(),
                utilisateurIamEntity.getPrenom(),
                utilisateurIamEntity.getEmail(),
                dateCreation
        );
    }

    @Override
    public UtilisateurIamEntity mapDtoToEntity(UtilisateurIamSimpleDto utilisateurIamSimpleDto) {
        // TODO : probablement pas nécessaire
        return null;
    }
}
