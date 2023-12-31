package org.epita.exposition.mapper.selection.etiquette;

import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.dto.selection.EtiquetteDto;
import org.epita.exposition.dto.utilisateur.UtilisateurDto;
import org.springframework.stereotype.Component;

@Component
public class EtiquetteMapper extends Mapper<EtiquetteEntity, EtiquetteDto> {
    private Mapper<UtilisateurEntity, UtilisateurDto> mapper;

    public EtiquetteMapper(Mapper<UtilisateurEntity, UtilisateurDto> mapper) {
        this.mapper = mapper;
    }

    @Override
    public EtiquetteDto mapEntityToDto(EtiquetteEntity input) {
        return new EtiquetteDto(
                input.getId(),
                input.getNomTag(),
                input.getUtilisateurEntity().getId());
    }

    @Override
    public EtiquetteEntity mapDtoToEntity(EtiquetteDto input) {
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(input.getIdUtilisateur());

        return new EtiquetteEntity(
                input.getId(),
                input.getNomTag(),
                utilisateur);
    }
}
