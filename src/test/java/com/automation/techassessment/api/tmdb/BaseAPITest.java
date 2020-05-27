package com.automation.techassessment.api.tmdb;

import com.automation.techassessment.api.endpoints.ApiEndpoints;
import com.automation.techassessment.api.endpoints.authentication.GuestSessionResponse;
import com.automation.techassessment.api.endpoints.movies.KeywordsResponseMovies;
import com.automation.techassessment.api.endpoints.movies.MoviesKeywords;
import com.automation.techassessment.api.endpoints.movies.MoviesResult;
import com.automation.techassessment.api.endpoints.movies.SearchResponseMovies;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

import static com.automation.techassessment.api.errors.RestAssertions.assertCallSucceeds;

public class BaseAPITest {

    ApiEndpoints rest;

    @BeforeMethod
    public void setup() throws Exception {
        rest = ApiEndpoints.newBuilder().build();
    }

    public String getGuestSessionID() {
        GuestSessionResponse guestSession = assertCallSucceeds(rest.authentication.createGuestSession()).body();
        return guestSession.getGuestSessionID();
    }

    public List<String> getMovieKeywords(String movieID) {
        KeywordsResponseMovies keywordsResult = assertCallSucceeds(rest.movie.getMovieKeywords(movieID)).body();
        List<String> list = new ArrayList<String>();
        List<MoviesKeywords> keywords = keywordsResult.getKeywords();
        for (MoviesKeywords keyword: keywords) {
            list.add(keyword.getName());
        }
        return list;
    }

    public String getMovieID(String searchQuery) {
        int movieID = 0;
        SearchResponseMovies search = assertCallSucceeds(rest.movie.searchMovie(searchQuery)).body();
        List<MoviesResult> results = search.getResults();
        for (MoviesResult result: results) {
            if (result.getTitle().equals(searchQuery)) {
                movieID = result.getId();
            }
        }
        Assert.assertNotEquals(movieID, 0, "Searched movie was not found");
        return String.valueOf(movieID);
    }
}
