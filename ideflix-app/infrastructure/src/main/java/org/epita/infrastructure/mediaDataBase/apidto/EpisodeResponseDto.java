package org.epita.infrastructure.mediaDataBase.apidto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.List;

public class EpisodeResponseDto {

    @JsonFormat(pattern="yyyy-MM-dd")
    private String air_date;
    List<Object> crew = new ArrayList<Object>();
    private int episode_number;
    List<Object> guest_stars = new ArrayList<Object>();
    private String name;
    private String overview;
    private long id;
    private String production_code;
    private int runtime;
    private int season_number;
    private String still_path;
    private float vote_average;
    private int vote_count;

    public EpisodeResponseDto() {
    }

    public EpisodeResponseDto(String air_date, List<Object> crew, int episode_number, List<Object> guest_stars, String name, String overview, long id, String production_code, int runtime, int season_number, String still_path, float vote_average, int vote_count) {
        this.air_date = air_date;
        this.crew = crew;
        this.episode_number = episode_number;
        this.guest_stars = guest_stars;
        this.name = name;
        this.overview = overview;
        this.id = id;
        this.production_code = production_code;
        this.runtime = runtime;
        this.season_number = season_number;
        this.still_path = still_path;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public List<Object> getCrew() {
        return crew;
    }

    public void setCrew(List<Object> crew) {
        this.crew = crew;
    }

    public int getEpisode_number() {
        return episode_number;
    }

    public void setEpisode_number(int episode_number) {
        this.episode_number = episode_number;
    }

    public List<Object> getGuest_stars() {
        return guest_stars;
    }

    public void setGuest_stars(List<Object> guest_stars) {
        this.guest_stars = guest_stars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduction_code() {
        return production_code;
    }

    public void setProduction_code(String production_code) {
        this.production_code = production_code;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public String getStill_path() {
        return still_path;
    }

    public void setStill_path(String still_path) {
        this.still_path = still_path;
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

    @Override
    public String toString() {
        return "EpisodeResponseDto{" +
                "air_date='" + air_date + '\'' +
                ", crew=" + crew +
                ", episode_number=" + episode_number +
                ", guest_stars=" + guest_stars +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", id=" + id +
                ", production_code='" + production_code + '\'' +
                ", runtime=" + runtime +
                ", season_number=" + season_number +
                ", still_path='" + still_path + '\'' +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                '}';
    }
}
