package com.automation.techassessment.api.tmdb;

import com.automation.techassessment.api.endpoints.ApiEndpoints;
import com.automation.techassessment.api.endpoints.movies.MoviesResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.automation.techassessment.api.errors.RestAssertions.assertCallSucceeds;

public class MovieAssessmentSearchTest {
    private ApiEndpoints rest;

    @BeforeMethod
    public void setup() throws Exception {
        rest = ApiEndpoints.newBuilder().build();
    }

    @Test(dataProvider = "movieNames")
        /*
    The most important test in this series, we are expecting users to get a result in every movie search
     */
    public void searchMovieByOriginalTitle(String name) {

        Assert.assertEquals(assertCallSucceeds(rest.movie.searchMovie(name)).code(), 200, "Movie search is not getting any result");
    }

    @Test(dataProvider = "movieIDs")
    /*
    As part of UX each result should be displayed along with its principal information, most valuable are
    Title, Release date, Original language and Poster.
     */
    public void verifyDisplayedMovieInfo(String id) {

        SoftAssert softAssert = new SoftAssert();
        MoviesResponse moviesResponse = assertCallSucceeds(rest.movie.getMovieGeneralInfo(id)).body();
        softAssert.assertTrue(!moviesResponse.getTitle().isEmpty(), "The title of the movie is not displayed");
        softAssert.assertTrue(!moviesResponse.getReleaseDate().isEmpty(),"The release date of the movie is not displayed");
        softAssert.assertTrue(!moviesResponse.getOriginalLanguage().isEmpty(), "The language of the movie is not displayed");
        softAssert.assertTrue(!moviesResponse.getPosterPath().isEmpty(), "The Poster of the movie is not displayed");
        softAssert.assertAll();
    }

    @Test
    /*
    As part of our homepage the latest added movie its suggested on a banner at the top of the page this test ensures
    a movie is shown.
     */
    public void thisisatest3() {

        SoftAssert softAssert = new SoftAssert();
        MoviesResponse moviesResponse = assertCallSucceeds(rest.movie.getLatestMovie()).body();
        softAssert.assertTrue(!moviesResponse.getTitle().isEmpty(), "The title of the latest movie is not displayed");

    }

    @DataProvider
    public Object[][] movieIDs() {
        return new Object[][]{
                {"399174"}, {"141"}, {"198184"}
        };
    }

    @DataProvider
    public Object[][] movieNames() {
        return new Object[][]{
                {"Back to the future"}, {"Jojo"}, {"?!=44??"}
        };
    }


    //TODO: Create more test scenarios for searching movies

    @Test(enabled = false)
    public void searchMoviesByAlternativetitles() {
        /*
        Write code for validate that its possible to get the expected result from a movie searching
        by its alternative titles, movie always should display its primary info (title, year, languages)
         */
    }

    @Test(enabled = false)
    public void searchMoviesByGenre() {
        /*
        This test should validate that tis possible to get a movie results list by trying a search via movie genre
         */
    }

    @Test(enabled = false)
    public void searchUnavailableMovie() {
        /*
        If the user is looking for a movie that is not available for playing at the time we should at least
         display 1 similar movie according to its genre tags.
         */
    }

    //TODO: It would be amazing if we include some scripts in order to test our vulnerabilities **need to learn how**

    @Test(enabled = false)
    public void connectWithStolenAPIKey() {
        /*
        This test should verify that only the person who owns a credential can request info from the service
         */
    }

    @Test(enabled = false)
    public void verifyCookiesAreProtected() {
        /*
        This test should search for cookie security vulnerabilities to avoid someone trying to generate session tokens.
         */
    }


}