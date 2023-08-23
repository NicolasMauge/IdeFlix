package org.epita.infrastructure.mediaDataBase.apidto;

import java.util.List;

public class AllGenresResponseDto {

    private List<GenreResponseDto> genres;

    public AllGenresResponseDto() {
    }

    public AllGenresResponseDto(List<GenreResponseDto> genres) {
        this.genres = genres;
    }

    public List<GenreResponseDto> getGenres() {
        return genres;
    }

//    public void setGenres(List<GenreResponseDto> genres) {
//        this.genres = genres;
//    }


    @Override
    public String toString() {
        return "AllGenresResponseDto{" +
                "genres=" + genres +
                '}';
    }
}
