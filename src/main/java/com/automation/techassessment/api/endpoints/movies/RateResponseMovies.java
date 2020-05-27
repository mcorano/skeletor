package com.automation.techassessment.api.endpoints.movies;

import com.automation.techassessment.api.model.TMDbObject;

public class RateResponseMovies implements TMDbObject {

    private String status_message = null;

    public String getStatusMessage() {
        return status_message;
    }
}
