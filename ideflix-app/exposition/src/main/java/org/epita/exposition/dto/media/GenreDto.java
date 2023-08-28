package org.epita.exposition.dto.media;

import io.swagger.v3.oas.annotations.media.Schema;

public class GenreDto {
    @Schema(description = "Identifiant IdeFlix interne", example = "1")
    private Long id;
    @Schema(description = "Identifiant du fournisseur de données (The Movie Database)", example = "878")
    private String idTmdb;
    @Schema(description = "Libellé du genre", example = "Science-Fiction")
    private String nomGenre;

    public GenreDto() {
    }

    public GenreDto(Long id, String idTmdb, String nomGenre) {
        this.id = id;
        this.idTmdb = idTmdb;
        this.nomGenre = nomGenre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTmdb() {
        return idTmdb;
    }

    public void setIdTmdb(String idTmdb) {
        this.idTmdb = idTmdb;
    }

    public String getNomGenre() {
        return nomGenre;
    }

    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }
}
