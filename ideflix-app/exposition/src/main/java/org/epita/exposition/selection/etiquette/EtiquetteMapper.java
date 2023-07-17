package org.epita.exposition.selection.etiquette;

import org.epita.application.utilisateur.utilisateur.UtilisateurService;
import org.epita.domaine.selection.EtiquetteEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.exposition.common.Mapper;
import org.epita.exposition.utilisateur.utilisateur.UtilisateurDto;
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
                input.getNomTag(),
                input.getUtilisateurEntity().getId());
    }

    @Override
    public EtiquetteEntity mapDtoToEntity(EtiquetteDto input) {
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(input.getIdUtilisateur());

        return new EtiquetteEntity(
                input.getNomTag(),
                utilisateur);
    }
}
