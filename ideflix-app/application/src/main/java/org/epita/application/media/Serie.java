package org.epita.application.media;

import java.util.List;

public interface Serie {
    void creerSerie(Serie serie);

    Serie trouverSerieParId(Long id);

    List<Serie> trouverToutesLesSeries();

    void supprimerSerie(Serie serie);
}
