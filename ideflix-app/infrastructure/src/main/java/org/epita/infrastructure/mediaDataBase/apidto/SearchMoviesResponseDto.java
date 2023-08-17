package org.epita.infrastructure.mediaDataBase.apidto;

import java.util.List;

public class SearchMoviesResponseDto {

    private int page;
    List<MovieLightResponseDto> results;
    private int total_pages;
    private int total_results;

    public SearchMoviesResponseDto() {
    }

    public SearchMoviesResponseDto(int page, List<MovieLightResponseDto> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieLightResponseDto> getResults() {
        return results;
    }

    public void setResults(List<MovieLightResponseDto> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    @Override
    public String toString() {
        return "SearchMoviesResponseDto{" +
                "page=" + page +
                ", results=" + results +
                ", total_pages=" + total_pages +
                ", total_results=" + total_results +
                '}';
    }
}
