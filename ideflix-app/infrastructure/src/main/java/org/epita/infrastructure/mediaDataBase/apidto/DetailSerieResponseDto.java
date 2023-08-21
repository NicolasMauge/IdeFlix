package org.epita.infrastructure.mediaDataBase.apidto;

import java.util.ArrayList;
import java.util.List;

public class DetailSerieResponseDto extends DetailMediaResponseDto {

    ArrayList<Object> created_by = new ArrayList<Object>();
    ArrayList<Object> episode_run_time = new ArrayList<Object>();
    private String first_air_date;

    private boolean in_production;
    ArrayList<Object> languages = new ArrayList<Object>();
    private String last_air_date;
    private Object last_episode_to_air;
    private String name;
    private Object next_episode_to_air;
    ArrayList<Object> networks = new ArrayList<Object>();
    private int number_of_episodes;
    private int number_of_seasons;
    ArrayList<Object> origin_country = new ArrayList<Object>();

    private String original_name;

    ArrayList<SaisonResponseDto> seasons = new ArrayList<SaisonResponseDto>();

    private String type;

    public DetailSerieResponseDto() {
    }

    public DetailSerieResponseDto(boolean adult, String backdrop_path, List<GenreResponseDto> genres, String homepage, long id, String original_language, String overview, float popularity, String poster_path, ArrayList<Object> production_companies, ArrayList<Object> production_countries, ArrayList<Object> spoken_languages, String status, String tagline, float vote_average, int vote_count, ArrayList<Object> created_by, ArrayList<Object> episode_run_time, String first_air_date, boolean in_production, ArrayList<Object> languages, String last_air_date, Object last_episode_to_air, String name, Object next_episode_to_air, ArrayList<Object> networks, int number_of_episodes, int number_of_seasons, ArrayList<Object> origin_country, String original_name, ArrayList<SaisonResponseDto> seasons, String type) {
        super(adult, backdrop_path, genres, homepage, id, original_language, overview, popularity, poster_path, production_companies, production_countries, spoken_languages, status, tagline, vote_average, vote_count);
        this.created_by = created_by;
        this.episode_run_time = episode_run_time;
        this.first_air_date = first_air_date;
        this.in_production = in_production;
        this.languages = languages;
        this.last_air_date = last_air_date;
        this.last_episode_to_air = last_episode_to_air;
        this.name = name;
        this.next_episode_to_air = next_episode_to_air;
        this.networks = networks;
        this.number_of_episodes = number_of_episodes;
        this.number_of_seasons = number_of_seasons;
        this.origin_country = origin_country;
        this.original_name = original_name;
        this.seasons = seasons;
        this.type = type;
    }

    public ArrayList<Object> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(ArrayList<Object> created_by) {
        this.created_by = created_by;
    }

    public ArrayList<Object> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(ArrayList<Object> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public ArrayList<Object> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Object> languages) {
        this.languages = languages;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public Object getLast_episode_to_air() {
        return last_episode_to_air;
    }

    public void setLast_episode_to_air(Object last_episode_to_air) {
        this.last_episode_to_air = last_episode_to_air;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNext_episode_to_air() {
        return next_episode_to_air;
    }

    public void setNext_episode_to_air(Object next_episode_to_air) {
        this.next_episode_to_air = next_episode_to_air;
    }

    public ArrayList<Object> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<Object> networks) {
        this.networks = networks;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public ArrayList<Object> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(ArrayList<Object> origin_country) {
        this.origin_country = origin_country;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public ArrayList<SaisonResponseDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<SaisonResponseDto> seasons) {
        this.seasons = seasons;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DetailSerieResponseDto{" +
                "created_by=" + created_by +
                ", episode_run_time=" + episode_run_time +
                ", first_air_date='" + first_air_date + '\'' +
                ", in_production=" + in_production +
                ", languages=" + languages +
                ", last_air_date='" + last_air_date + '\'' +
                ", last_episode_to_air=" + last_episode_to_air +
                ", name='" + name + '\'' +
                ", next_episode_to_air=" + next_episode_to_air +
                ", networks=" + networks +
                ", number_of_episodes=" + number_of_episodes +
                ", number_of_seasons=" + number_of_seasons +
                ", origin_country=" + origin_country +
                ", original_name='" + original_name + '\'' +
                ", seasons=" + seasons +
                ", type='" + type + '\'' +
                ", genres=" + genres +
                ", production_companies=" + production_companies +
                ", production_countries=" + production_countries +
                ", spoken_languages=" + spoken_languages +
                '}';
    }
}
