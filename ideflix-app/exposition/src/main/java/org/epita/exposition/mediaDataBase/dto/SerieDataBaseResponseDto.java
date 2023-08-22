package org.epita.exposition.mediaDataBase.dto;

import org.epita.exposition.dto.common.TypeMedia;

import java.util.List;

public class SerieDataBaseResponseDto extends MediaDataBaseResponseDto{

    private List<SaisonDataBaseResponseDto> saisonDataBaseResponseDtos;

    public SerieDataBaseResponseDto() {
    }

    public SerieDataBaseResponseDto(long idDataBase, String titre, String dateSortie, int duree, String resume, String cheminAffichePortrait, String cheminAffichePaysage, float noteDataBase, List<GenreDataBaseResponseDto> genreDataBaseResponseDtos, TypeMedia typeMedia, int nombreSaisons, List<SaisonDataBaseResponseDto> saisonDataBaseResponseDtos) {
        super(idDataBase, titre, dateSortie, duree, resume, cheminAffichePortrait, cheminAffichePaysage, noteDataBase, genreDataBaseResponseDtos, typeMedia, nombreSaisons);
        this.saisonDataBaseResponseDtos = saisonDataBaseResponseDtos;
    }

    public List<SaisonDataBaseResponseDto> getSaisonDataBaseResponseDtos() {
        return saisonDataBaseResponseDtos;
    }

    public void setSaisonDataBaseResponseDtos(List<SaisonDataBaseResponseDto> saisonDataBaseResponseDtos) {
        this.saisonDataBaseResponseDtos = saisonDataBaseResponseDtos;
    }

    @Override
    public String toString() {
        return "SerieDataBaseResponseDto{" +
                "saisonDataBaseResponseDtos=" + saisonDataBaseResponseDtos +
                '}';
    }
}
