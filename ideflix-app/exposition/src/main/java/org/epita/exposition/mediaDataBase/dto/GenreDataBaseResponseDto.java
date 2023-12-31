package org.epita.exposition.mediaDataBase.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class GenreDataBaseResponseDto {

    // DTO en réponse vers IHM

    @Schema(description = "identifiant de la movie Data Base", example = "18", requiredMode = Schema.RequiredMode.REQUIRED)
    private int idDataBase;
    @Schema(description = "nom du genre", example = "Action", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nomGenre;

    public GenreDataBaseResponseDto() {
    }

    public GenreDataBaseResponseDto(int idDataBase, String nomGenre) {
        this.idDataBase = idDataBase;
        this.nomGenre = nomGenre;
    }

    public int getIdDataBase() {
        return idDataBase;
    }

    public void setIdDataBase(int idDataBase) {
        this.idDataBase = idDataBase;
    }

    public String getNomGenre() {
        return nomGenre;
    }

    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    @Override
    public String toString() {
        return "GenreDataBaseResponseDto{" +
                "idDataBase=" + idDataBase +
                ", nomGenre='" + nomGenre + '\'' +
                '}';
    }
}
