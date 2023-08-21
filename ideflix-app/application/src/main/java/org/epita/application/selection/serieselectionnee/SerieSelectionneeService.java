package org.epita.application.selection.serieselectionnee;

import org.epita.domaine.selection.SerieSelectionneeEntity;
import org.epita.domaine.utilisateur.UtilisateurEntity;

import java.util.List;
import java.util.Optional;

public interface SerieSelectionneeService {
    void creerSerieSelectionnee(SerieSelectionneeEntity serieSelectionneeEntity);

    SerieSelectionneeEntity trouverSerieSelectionneeParId(Long id);

    List<SerieSelectionneeEntity> trouverToutesLesSeriesSelectionnees();

    void supprimerSerieSelectionneeParId(Long id);

    List<SerieSelectionneeEntity> trouverSerieParUtilisateur(UtilisateurEntity utilisateurEntity);
    List<SerieSelectionneeEntity> trouverSeriesSelectionneesParEmailUtilisateur(String email);

    List<SerieSelectionneeEntity> trouverSeriesSelectionneesParEmailUtilisateurEtIdTmdb(String email, String idTmdb);
}
