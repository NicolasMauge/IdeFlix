package org.epita.exposition.mapper.utilisateur;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.utilisateur.PreferencesUtilisateurDto;
import org.epita.exposition.dto.utilisateur.UtilisateurEtPrefDto;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurEtPrefMapper extends Mapper<UtilisateurEntity, UtilisateurEtPrefDto> {
    private Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> preferencesUtilisateurMapper;

    public UtilisateurEtPrefMapper(Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> preferencesUtilisateurMapper) {
        this.preferencesUtilisateurMapper = preferencesUtilisateurMapper;
    }

    @Override
    public UtilisateurEtPrefDto mapEntityToDto(UtilisateurEntity input) {
        return new UtilisateurEtPrefDto(
                input.getId(),
                input.getEmail(),
                input.getNom(),
                input.getPrenom(),
                input.getDateCreation(),
                this.preferencesUtilisateurMapper.mapEntityToDto(input.getPreferencesUtilisateurEntity())
        );
    }

    @Override
    public UtilisateurEntity mapDtoToEntity(UtilisateurEtPrefDto input) {
        return new UtilisateurEntity(
                input.getId(),
                input.getEmail(),
                input.getNom(),
                input.getPrenom(),
                input.getDateCreation(),
                this.preferencesUtilisateurMapper.mapDtoToEntity(input.getPreferencesUtilisateur())
        );
    }
}
