package org.epita.application.mediaDataBase.serieDataBase;

import org.epita.domaine.mediaDataBase.SerieDataBase;
import org.epita.infrastructure.mediaDataBase.SerieDataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieDataBaseServiceImpl implements SerieDataBaseService {
    private static final Logger logger = LoggerFactory.getLogger(SerieDataBaseServiceImpl.class);


    SerieDataBaseRepository serieDataBaseRepository;

    public SerieDataBaseServiceImpl(SerieDataBaseRepository serieDataBaseRepository) {
        this.serieDataBaseRepository = serieDataBaseRepository;
    }

    @Override
    public List<SerieDataBase> searchSeries(String query) {
        logger.debug("recherche liste séries selon " + query);
        return serieDataBaseRepository.searchSeriesDataBase(query);
    }

    @Override
    public SerieDataBase findSerieById(long Id) {
        logger.debug("recherche détail d'une série selon id" + Id);
        return serieDataBaseRepository.findDetailSerieDataBase(Id);
    }
}
