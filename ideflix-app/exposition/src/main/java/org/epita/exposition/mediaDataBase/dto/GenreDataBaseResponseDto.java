package org.epita.exposition.mediaDataBase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.epita.exposition.iam.utilisateuriam.dto.RoleIamDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class GenreDataBaseResponseDto {

    // DTO en réponse vers IHM

    @Schema(name = "identifiant de la movie Data Base", example = "18", required = true)
    private int idDataBase;
    @Schema(name = "nom du genre", example = "Action", required = true)
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
