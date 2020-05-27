package com.automation.techassessment.api.endpoints.movies;

public class MoviesRateBody {

    private double value;

    public MoviesRateBody(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
