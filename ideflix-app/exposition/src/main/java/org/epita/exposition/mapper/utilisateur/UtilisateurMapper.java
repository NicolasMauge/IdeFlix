package org.epita.exposition.mapper.utilisateur;

import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.utilisateur.UtilisateurDto;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper extends Mapper<UtilisateurEntity, UtilisateurDto> {
    @Override
    public UtilisateurDto mapEntityToDto(UtilisateurEntity input) {
        return new UtilisateurDto(
                input.getId(),
                input.getEmail(),
                input.getNom(),
                input.getPrenom(),
                input.getDateCreation());
    }

    @Override
    public UtilisateurEntity mapDtoToEntity(UtilisateurDto input) {
        return new UtilisateurEntity(
                input.getId(),
                input.getEmail(),
                input.getNom(),
                input.getPrenom(),
                input.getDateCreation());
    }
}
