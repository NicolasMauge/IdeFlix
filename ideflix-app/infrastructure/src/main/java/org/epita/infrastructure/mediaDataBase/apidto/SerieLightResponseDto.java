package org.epita.infrastructure.mediaDataBase.apidto;

import java.util.List;

public class SerieLightResponseDto extends MediaLightResponseDto{

    private List<String> origin_country;

    private String original_name;

    private String first_air_date;
    private String name;


    public SerieLightResponseDto() {
    }

    public SerieLightResponseDto(boolean adult, String backdrop_path, List<Integer> genre_ids, long id, String original_language, String overview, float popularity, String poster_path, String media_Type, float vote_average, int vote_count, List<String> origin_country, String original_name, String first_air_date, String name) {
        super(adult, backdrop_path, genre_ids, id, original_language, overview, popularity, poster_path, media_Type, vote_average, vote_count);
        this.origin_country = origin_country;
        this.original_name = original_name;
        this.first_air_date = first_air_date;
        this.name = name;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SerieLightResponseDto{" +
                "origin_country=" + origin_country +
                ", original_name='" + original_name + '\'' +
                ", first_air_date='" + first_air_date + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
