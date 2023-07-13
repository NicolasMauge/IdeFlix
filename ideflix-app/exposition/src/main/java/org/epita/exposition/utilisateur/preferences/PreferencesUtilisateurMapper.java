package org.epita.exposition.utilisateur.preferences;

import org.epita.application.utilisateur.preferences.PreferencesUtilisateurService;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreMapper;
import org.springframework.stereotype.Component;

@Component
public class PreferencesUtilisateurMapper extends Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> {
    private GenreMapper genreMapper;
    private PreferencesUtilisateurService preferencesUtilisateurService;

    public PreferencesUtilisateurMapper(GenreMapper genreMapper, PreferencesUtilisateurService preferencesUtilisateurService) {
        this.genreMapper = genreMapper;
        this.preferencesUtilisateurService = preferencesUtilisateurService;
    }

    @Override
    public PreferencesUtilisateurDto mapEntityToDto(PreferencesUtilisateurEntity input) {
        return new PreferencesUtilisateurDto(
                input.getId(),
                input.getPseudo(),
                genreMapper.mapListEntityToDto(input.getGenreList()));
    }

    @Override
    public PreferencesUtilisateurEntity mapDtoToEntity(PreferencesUtilisateurDto input) {
        // TODO : attention, si on convertit un dto qui n'est pas complètement rempli, les valeurs nulls vont écraser les valeurs en base
        return new PreferencesUtilisateurEntity(
                input.getId(),
                input.getPseudo(),
                genreMapper.mapListDtoToEntity(input.getGenreList()));
    }
}
