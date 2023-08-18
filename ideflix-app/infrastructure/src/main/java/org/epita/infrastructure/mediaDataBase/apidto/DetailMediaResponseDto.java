package org.epita.infrastructure.mediaDataBase.apidto;

import java.util.ArrayList;
import java.util.List;

public class DetailMediaResponseDto {

    private boolean adult;
    private String backdrop_path;

    List<GenreResponseDto> genres = new ArrayList<GenreResponseDto>();
    private String homepage;
    private long id;
    private String original_language;

    private String overview;
    private float popularity;
    private String poster_path;
    ArrayList<Object> production_companies = new ArrayList<Object>();
    ArrayList<Object> production_countries = new ArrayList<Object>();
    ArrayList<Object> spoken_languages = new ArrayList<Object>();
    private String status;
    private String tagline;

    private float vote_average;
    private int vote_count;

    public DetailMediaResponseDto() {
    }

    public DetailMediaResponseDto(boolean adult, String backdrop_path, List<GenreResponseDto> genres, String homepage, long id, String original_language, String overview, float popularity, String poster_path, ArrayList<Object> production_companies, ArrayList<Object> production_countries, ArrayList<Object> spoken_languages, String status, String tagline, float vote_average, int vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.original_language = original_language;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
        this.spoken_languages = spoken_languages;
        this.status = status;
        this.tagline = tagline;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<GenreResponseDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreResponseDto> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public ArrayList<Object> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(ArrayList<Object> production_companies) {
        this.production_companies = production_companies;
    }

    public ArrayList<Object> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(ArrayList<Object> production_countries) {
        this.production_countries = production_countries;
    }

    public ArrayList<Object> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(ArrayList<Object> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }
}
