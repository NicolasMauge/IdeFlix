package org.epita.exposition.mapper.utilisateur;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.media.GenreEntity;
import org.epita.domaine.utilisateur.PreferencesUtilisateurEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.media.GenreDto;
import org.epita.exposition.dto.utilisateur.PreferencesUtilisateurDto;
import org.springframework.stereotype.Component;

@Component
public class PreferencesUtilisateurMapper extends Mapper<PreferencesUtilisateurEntity, PreferencesUtilisateurDto> {
    private Mapper<GenreEntity, GenreDto> genreMapper;
    private UtilisateurService utilisateurService;

    public PreferencesUtilisateurMapper(Mapper<GenreEntity, GenreDto> genreMapper, UtilisateurService utilisateurService) {
        this.genreMapper = genreMapper;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public PreferencesUtilisateurDto mapEntityToDto(PreferencesUtilisateurEntity input) {
        return new PreferencesUtilisateurDto(
                input.getPseudo(),
                input.getUtilisateur()!=null?input.getUtilisateur().getEmail():null,
                genreMapper.mapListEntityToDto(input.getGenreList()));
    }

    @Override
    public PreferencesUtilisateurEntity mapDtoToEntity(PreferencesUtilisateurDto input) {
        UtilisateurEntity utilisateur = this.utilisateurService.trouverUtilisateurParEmail(input.getEmail());

        return new PreferencesUtilisateurEntity(
                null,
                input.getPseudo(),
                utilisateur,
                genreMapper.mapListDtoToEntity(input.getGenreList()));
    }
}
