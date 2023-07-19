package org.epita.domaine.selection;

import org.epita.domaine.media.MediaAudioVisuelEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class FilmSelectionneEntity extends MediaSelectionneEntity {
    public FilmSelectionneEntity() {
    }

    public FilmSelectionneEntity(Boolean avisPouce, LocalDate dateSelection, List<EtiquetteEntity> etiquetteEntityList,
                                 StatutMediaEntity statutMediaEntity, MediaAudioVisuelEntity mediaAudioVisuelEntity,
                                 UtilisateurEntity utilisateurEntity) {
        super(avisPouce, dateSelection, etiquetteEntityList, statutMediaEntity, mediaAudioVisuelEntity, utilisateurEntity);
    }

    public FilmSelectionneEntity(Long id, Boolean avisPouce, LocalDate dateSelection, List<EtiquetteEntity> etiquetteEntityList,
                                 StatutMediaEntity statutMediaEntity, MediaAudioVisuelEntity mediaAudioVisuelEntity,
                                 UtilisateurEntity utilisateurEntity) {
        super(id, avisPouce, dateSelection, etiquetteEntityList, statutMediaEntity, mediaAudioVisuelEntity, utilisateurEntity);
    }
}
