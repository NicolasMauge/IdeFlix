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
    private UtilisateurService utilisateurService;

    public EtiquetteMapper(Mapper<UtilisateurEntity, UtilisateurDto> mapper, UtilisateurService utilisateurService) {
        this.mapper = mapper;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public EtiquetteDto mapEntityToDto(EtiquetteEntity input) {
        return new EtiquetteDto(
                input.getNomTag(),
                input.getUtilisateurEntity().getId());
    }

    @Override
    public EtiquetteEntity mapDtoToEntity(EtiquetteDto input) {
        // il faut aller chercher l'utilisateur déjà existant correspondant à l'id renseigné
        UtilisateurEntity utilisateurEntity = this.utilisateurService.trouverUtilisateurParId(input.getIdUtilisateur());

        return new EtiquetteEntity(
                input.getNomTag(),
                utilisateurEntity);
    }
}
