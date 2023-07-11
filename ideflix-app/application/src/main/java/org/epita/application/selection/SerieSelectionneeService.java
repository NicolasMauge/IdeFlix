package org.epita.application.selection;

import org.epita.domaine.selection.SerieSelectionneeEntity;

import java.util.List;
import java.util.Optional;

public interface SerieSelectionneeService {
    void creerSerieSelectionnee(SerieSelectionneeEntity serieSelectionneeEntity);

    Optional<SerieSelectionneeEntity> trouverSerieSelectionneeParId(Long id);

    List<SerieSelectionneeEntity> trouverToutesLesSeriesSelectionnees();

    void supprimerSerieSelectionneeParId(Long id);
}
