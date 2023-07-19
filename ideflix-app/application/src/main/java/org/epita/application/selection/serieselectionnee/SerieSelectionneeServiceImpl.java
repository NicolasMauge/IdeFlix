package org.epita.application.selection.serieselectionnee;

import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.selection.SerieSelectionneeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieSelectionneeServiceImpl implements SerieSelectionneeService {
    SerieSelectionneeRepository serieSelectionneeRepository;

    public SerieSelectionneeServiceImpl(SerieSelectionneeRepository serieSelectionneeRepository) {
        this.serieSelectionneeRepository = serieSelectionneeRepository;
    }

    @Override
    public void creerSerieSelectionnee(SerieSelectionneeEntity serieSelectionneeEntity) {
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
