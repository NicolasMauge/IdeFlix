package org.epita.application.media;

import org.epita.domaine.media.SerieEntity;

import java.util.List;
import java.util.Optional;

public interface SerieService {
    void creerSerie(SerieEntity serieEntity);

    Optional<SerieEntity> trouverSerieParId(Long id);

    List<SerieEntity> trouverToutesLesSeries();

    void supprimerSerieParId(Long id);
}
