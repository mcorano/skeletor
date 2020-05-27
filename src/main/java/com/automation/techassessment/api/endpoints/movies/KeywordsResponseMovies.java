package com.automation.techassessment.api.endpoints.movies;

import com.automation.techassessment.api.model.TMDbObject;

import java.util.List;

public class KeywordsResponseMovies implements TMDbObject {

    private int id;
    List<MoviesKeywords> keywords;

    public int getId() {

        return id;
    }

    public List<MoviesKeywords> getKeywords() {

        return keywords;
    }
}
