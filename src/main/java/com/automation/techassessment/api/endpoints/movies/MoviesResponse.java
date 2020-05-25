package com.automation.techassessment.api.endpoints.movies;

import com.automation.techassessment.api.model.TMDbObject;

public class MoviesResponse implements TMDbObject {

    private String release_date;
    private String title;
    private float vote_average;
    private String original_language;
    private int id;
    private int page;
    private int total_results;
    private String poster_path;
    private float popularity;

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public int getTotalResults() {
        return total_results;
    }

    public int getPage() {
        return page;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public int getID() {
        return id;
    }



}
