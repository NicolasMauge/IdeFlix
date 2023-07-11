package org.epita.application.media;

import org.epita.domaine.media.SerieEntity;
import org.epita.infrastructure.media.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieServiceImpl implements SerieService {
    private SerieRepository serieRepository;

    public SerieServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public void creerSerie(SerieEntity serieEntity) {
        this.serieRepository.save(serieEntity);
    }

    @Override
    public Optional<SerieEntity> trouverSerieParId(Long id) {
        return this.serieRepository.findById(id);
    }

    @Override
    public List<SerieEntity> trouverToutesLesSeries() {
        return this.serieRepository.findAll();
    }

    @Override
    public void supprimerSerieParId(Long id) {
        this.serieRepository.deleteById(id);
    }
}
