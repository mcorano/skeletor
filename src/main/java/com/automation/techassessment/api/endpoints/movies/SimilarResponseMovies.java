package com.automation.techassessment.api.endpoints.movies;

import com.automation.techassessment.api.model.TMDbObject;

import java.util.List;

public class SimilarResponseMovies implements TMDbObject {

    private List<MoviesSimilar> results = null;

    public List<MoviesSimilar> getResults() {
        return results;
    }
}
