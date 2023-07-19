package org.epita.exposition.mapper.utilisateur;

import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.mapper.media.genre.GenreMapper;
import org.epita.exposition.dto.utilisateur.PreferencesUtilisateurDto;
import org.springframework.stereotype.Component;

@Component
public class PreferencesUtilisateurMapper extends Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> {
    private Mapper<GenreEntity, GenreDto> genreMapper;

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
        // TODO : attention, si on convertit un dto qui n'est pas complètement rempli, les valeurs nulls vont écraser les valeurs en base
        return new PreferencesUtilisateurEntity(
                input.getId(),
                input.getPseudo(),
                genreMapper.mapListDtoToEntity(input.getGenreList()));
    }
}
