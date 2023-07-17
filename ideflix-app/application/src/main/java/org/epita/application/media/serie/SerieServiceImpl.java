package org.epita.application.media.serie;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
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
    public SerieEntity trouverSerieParId(Long id) {
        Optional<SerieEntity> serieEntityOptional = this.serieRepository.findById(id);
        if(serieEntityOptional.isPresent()) {
            return serieEntityOptional.get();
        }
        throw new EntityNotFoundException("Série non trouvée");
    }

    @Override
    public List<SerieEntity> trouverToutesLesSeries() {
        return this.serieRepository.findAll();
    }

    @Override
    public void supprimerSerieParId(Long id) {
        this.serieRepository.deleteById(id);
    }

    @Override
    public SerieEntity trouverSerieParIdTmdb(String idTmdb) {
        Optional<SerieEntity> serieEntityOptional = this.serieRepository.findByIdTmdb(idTmdb);
        if(serieEntityOptional.isPresent()) {
            return serieEntityOptional.get();
        }
        throw new EntityNotFoundException("Série non trouvée");
    }
}
