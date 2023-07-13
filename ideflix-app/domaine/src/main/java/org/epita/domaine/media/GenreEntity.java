package org.epita.domaine.media;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idTmdb;

    private String nomGenre;

    public GenreEntity() {
    }

    public GenreEntity(String idTmdb, String nomGenre) {
        this.idTmdb = idTmdb;
        this.nomGenre = nomGenre;
    }

    public GenreEntity(Long id, String idTmdb, String nomGenre) {
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
