package org.epita.exposition.utilisateur.preferences;

import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.media.genre.GenreMapper;
import org.springframework.stereotype.Component;

@Component
public class PreferencesUtilisateurMapper extends Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> {
    GenreMapper genreMapper;

    public PreferencesUtilisateurMapper(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
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
        return new PreferencesUtilisateurEntity(input.getPseudo());
    }
}
