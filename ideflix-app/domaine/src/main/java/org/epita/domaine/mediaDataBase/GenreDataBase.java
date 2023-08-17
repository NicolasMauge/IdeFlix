package org.epita.domaine.mediaDataBase;

import java.util.List;

public class GenreDataBase {

    private int idDatabase;
    private String nomGenre;

    public GenreDataBase() {
    }

    public GenreDataBase(int idDatabase, String nomGenre) {
        this.idDatabase = idDatabase;
        this.nomGenre = nomGenre;
    }


    public int getIdDatabase() {
        return idDatabase;
    }

    public void setIdDatabase(int idDatabase) {
        this.idDatabase = idDatabase;
    }

    public String getNomGenre() {
        return nomGenre;
    }

    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    @Override
    public String toString() {
        return "GenreDataBase{" +
                "idDatabase=" + idDatabase +
                ", nomGenre='" + nomGenre + '\'' +
                '}';
    }
}
