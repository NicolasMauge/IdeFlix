package org.epita.application.selection.serieselectionnee;

import org.epita.application.media.serie.SerieService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.media.SerieRepository;
import org.epita.infrastructure.selection.SerieSelectionneeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieSelectionneeServiceImpl implements SerieSelectionneeService {
    SerieSelectionneeRepository serieSelectionneeRepository;
    SerieRepository serieRepository;
    SerieService serieService;

    public SerieSelectionneeServiceImpl(SerieSelectionneeRepository serieSelectionneeRepository, SerieRepository serieRepository, SerieService serieService) {
        this.serieSelectionneeRepository = serieSelectionneeRepository;
        this.serieRepository = serieRepository;
        this.serieService = serieService;
    }

    @Override
    public void creerSerieSelectionnee(SerieSelectionneeEntity serieSelectionneeEntity) {
        String idTmdb = serieSelectionneeEntity.getMediaAudioVisuelEntity().getIdTmdb();
        String email = serieSelectionneeEntity.getUtilisateurEntity().getEmail();

        Optional<SerieEntity> film = this.serieRepository.findByIdTmdb(idTmdb);
        if(film.isPresent()) {
            serieSelectionneeEntity.getMediaAudioVisuelEntity().setId(film.get().getId());
        } else {
            this.serieService.creerSerie((SerieEntity) serieSelectionneeEntity.getMediaAudioVisuelEntity());
        }

        Optional<SerieSelectionneeEntity> serieSelectionneeEntityOptional =
                // this.serieSelectionneeRepository.findSerieSelectionneeEntityByMediaAudioVisuelEntityIdTmdb(idTmdb);
                this.serieSelectionneeRepository.findSerieSelectionneeEntityByUtilisateurEntity_EmailAndMediaAudioVisuelEntity_IdTmdb(email, idTmdb);
        if(serieSelectionneeEntityOptional.isPresent()) {
            serieSelectionneeEntityOptional.get().setId(serieSelectionneeEntity.getId());
        }

        this.serieSelectionneeRepository.save(serieSelectionneeEntity);
    }

    @Override
    public SerieSelectionneeEntity trouverSerieSelectionneeParId(Long id) {
        Optional<SerieSelectionneeEntity> serieSelectionneeEntityOptional = this.serieSelectionneeRepository.findById(id);
        if(serieSelectionneeEntityOptional.isPresent()) {
            return serieSelectionneeEntityOptional.get();
        }
        throw new EntityNotFoundException("Série sélectionnée non trouvée");
    }

    @Override
    public List<SerieSelectionneeEntity> trouverToutesLesSeriesSelectionnees() {
        return this.serieSelectionneeRepository.findAll();
    }

    @Override
    public void supprimerSerieSelectionneeParId(Long id) {
        this.serieSelectionneeRepository.deleteById(id);
    }

    @Override
    public List<SerieSelectionneeEntity> trouverSerieParUtilisateur(UtilisateurEntity utilisateurEntity) {
        return this.serieSelectionneeRepository.findByUtilisateurEntity(utilisateurEntity);
    }

    @Override
    public List<SerieSelectionneeEntity> trouverSeriesSelectionneesParEmailUtilisateur(String email) {
        return this.serieSelectionneeRepository.findSerieSelectionneeEntitiesByUtilisateurEntityEmailIs(email);
    }
}
