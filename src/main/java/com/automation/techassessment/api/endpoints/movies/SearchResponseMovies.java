package com.automation.techassessment.api.endpoints.movies;

import com.automation.techassessment.api.endpoints.tv.TVResult;
import com.automation.techassessment.api.model.TMDbObject;

import java.util.List;

public class SearchResponseMovies implements TMDbObject {

    private List<MoviesResult> results = null;

    public List<MoviesResult> getResults() {
        return results;
    }

}