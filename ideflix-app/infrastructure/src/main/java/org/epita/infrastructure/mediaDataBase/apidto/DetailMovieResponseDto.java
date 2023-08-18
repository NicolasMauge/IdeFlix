package org.epita.infrastructure.mediaDataBase.apidto;

public class DetailMovieResponseDto extends DetailMediaResponseDto{

    private Object belongs_to_collection;
    private float budget;

    private String imdb_id;

    private String original_title;

    private String release_date;
    private float revenue;
    private int runtime;

    private String title;
    private boolean video;

    public DetailMovieResponseDto(Object belongs_to_collection, float budget, String imdb_id, String original_title, String release_date, float revenue, int runtime, String title, boolean video) {
        this.belongs_to_collection = belongs_to_collection;
        this.budget = budget;
        this.imdb_id = imdb_id;
        this.original_title = original_title;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.title = title;
        this.video = video;
    }

    public DetailMovieResponseDto() {
    }

    public Object getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(Object belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "DetailMovieResponseDto{" +
                "belongs_to_collection='" + belongs_to_collection + '\'' +
                ", budget=" + budget +
                ", imdb_id='" + imdb_id + '\'' +
                ", original_title='" + original_title + '\'' +
                ", release_date='" + release_date + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", genres=" + genres +
                ", production_companies=" + production_companies +
                ", production_countries=" + production_countries +
                ", spoken_languages=" + spoken_languages +
                '}';
    }
}
