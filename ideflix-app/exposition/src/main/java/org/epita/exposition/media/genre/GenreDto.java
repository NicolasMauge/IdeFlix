package org.epita.exposition.media.genre;

public class GenreDto {
    private Long id;

    private String idTmdb;

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
