package org.epita.exposition.utilisateur.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                input.getEmail(),
                input.getNom(),
                input.getPrenom(),
                input.getDateCreation(),
                null);
    }
}
