package com.automation.techassessment.api.endpoints.movies;

public class MoviesResult {

    private int id;
    private String title;
    private String original_title;

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
