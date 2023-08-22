package org.epita.application.selection.serieselectionnee;

import org.epita.application.media.film.FilmServiceImpl;
import org.epita.application.media.serie.SerieService;
import org.epita.domaine.common.EntityNotFoundException;
import org.epita.domaine.media.FilmEntity;
import org.epita.domaine.media.SerieEntity;
import org.epita.domaine.selection.FilmSelectionneEntity;
import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;
import org.epita.infrastructure.media.SerieRepository;
import org.epita.infrastructure.selection.SerieSelectionneeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SerieSelectionneeServiceImpl implements SerieSelectionneeService {
    public static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    SerieSelectionneeRepository serieSelectionneeRepository;
    SerieRepository serieRepository;
    SerieService serieService;

    public SerieSelectionneeServiceImpl(SerieSelectionneeRepository serieSelectionneeRepository, SerieRepository serieRepository, SerieService serieService) {
        this.serieSelectionneeRepository = serieSelectionneeRepository;
        this.serieRepository = serieRepository;
        this.serieService = serieService;
    }

    @Override
    public void creerSerieSelectionnee(SerieSelectionneeEntity serieSelectionnee) {
        String idTmdb = serieSelectionnee.getMediaAudioVisuelEntity().getIdTmdb();
        String email = serieSelectionnee.getUtilisateurEntity().getEmail();

        Optional<SerieEntity> film = this.serieRepository.findByIdTmdb(idTmdb);
        if(film.isPresent()) {
            serieSelectionnee.getMediaAudioVisuelEntity().setId(film.get().getId());
            logger.debug("IdeFlix - creerSerieSelectionnee - série déjà existante : {idTmdb : "+ serieSelectionnee.getMediaAudioVisuelEntity().getIdTmdb()+"}");
        }

        Optional<SerieSelectionneeEntity> serieSelectionneeEntityOptional =
                this.serieSelectionneeRepository.findSerieSelectionneeEntityByUtilisateurEntity_EmailAndMediaAudioVisuelEntity_IdTmdb(email, idTmdb);
        if(serieSelectionneeEntityOptional.isPresent()) {
            serieSelectionnee.setId(serieSelectionneeEntityOptional.get().getId());
            logger.debug("IdeFlix - creerSerieSelectionnee - serieSelectionnee déjà existante");
        }

        logger.debug("IdeFlix - creerSerieSelectionnee - détails : {id : "+serieSelectionnee.getId()
                +", titre du média : " + serieSelectionnee.getMediaAudioVisuelEntity().getTitre()
                +", id du média :" + serieSelectionnee.getMediaAudioVisuelEntity().getId()
                + ", idTmdb : " +serieSelectionnee.getMediaAudioVisuelEntity().getIdTmdb()+"}");

        this.serieSelectionneeRepository.save(serieSelectionnee);
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

    @Override
    public List<SerieSelectionneeEntity> trouverSeriesSelectionneesParEmailUtilisateurEtIdTmdb(String email, String idTmdb) {
        List<SerieSelectionneeEntity> seriesList = new ArrayList<>();

        Optional<SerieSelectionneeEntity> seriesOpt = this.serieSelectionneeRepository.findSerieSelectionneeEntityByUtilisateurEntity_EmailAndMediaAudioVisuelEntity_IdTmdb(email, idTmdb);
        if(seriesOpt.isPresent()) {
            seriesList.add(seriesOpt.get());
        }

        return seriesList;
    }
}
