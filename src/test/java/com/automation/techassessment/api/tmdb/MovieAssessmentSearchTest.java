package com.automation.techassessment.api.tmdb;

import com.automation.techassessment.api.endpoints.movies.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static com.automation.techassessment.api.errors.RestAssertions.assertCallSucceeds;

public class MovieAssessmentSearchTest extends BaseAPITest{

    @DataProvider
    public Object[][] movieNames() {
        return new Object[][]{
                {"Back to the Future"}, {"Men in Black"}, {"Jojo Rabbit"}
        };
    }

    @Test
    /*
    This test validate that our suggestions for similar movies have at least one matching keyword to let the user
    discover more amazing films.
     */
    public void movies_similarMovies_VerifySimilarMoviesHaveMatchingKeywords() {

        String searchQuery = "In Time";
        //Searching for movie's id.
        String movieID =getMovieID(searchQuery);
        // Now we need to store the movie related keywords
        List<String> movieKeywords = getMovieKeywords(movieID);
        // Getting the similar movies keywords to be compared with the original
        SimilarResponseMovies similarMovies = assertCallSucceeds(rest.movie.getSimilarMovies(movieID)).body();
        List <MoviesSimilar> results = similarMovies.getResults();
        //Time for compare each movie keyword with our original movie keywords
        for (MoviesSimilar result: results) {
            int similarities = 0;
            for (String originalKeyword: movieKeywords) {
                List<String> similarmoviesKeywords = getMovieKeywords(String.valueOf(result.getId()));
                for (int i = 0; i < similarmoviesKeywords.size(); i++) {
                    if ( originalKeyword.equals(similarmoviesKeywords.get(i))) {
                        similarities++;
                    }
                }
            }
            Assert.assertTrue(similarities > 0,
                    "One suggested similar movie doesn't have any matching keyword with the original");
        }
    }

    @Test
    /*
    Validates that a user must be authenticated before rating a movie
     */
    public void movies_rateMovie_AuthenticationFailed() {
        String searchQuery = "Donnie Darko";
        //Searching for movie's id.
        String movieID = getMovieID(searchQuery);
        try {
            //Validating the user does not have permission to access, expecting a 401 Unauthorized.
            Response <RateResponseMovies> response = null;
            response = rest.movie.postRate(movieID).execute();
            Assert.assertEquals(response.code(), 401,
                    "Authentication bypassed");
        } catch (IOException e) {
            StringBuilder errorMessage = new StringBuilder();
            String responseBody = "Trying to get response body caused IOException: " + e.getMessage();
            errorMessage.append(responseBody);
        }
    }

    @Test(dataProvider = "movieNames")
    /*
    Emulates a user that wants to give his rating to a seen movie, the test validate
    the success message at the post response.
     */
    public void movies_rateMovie_RatingMovieSuccessfully(String movieName) {
        double ratingValue = 9.5;
        //Creating a guest session
        String guestSessionID = getGuestSessionID();
        //Searching for movie's id.
        String movieID = getMovieID(movieName);
        //Creating request body for rating movies
        MoviesRateBody moviesRateBody = new MoviesRateBody(ratingValue);
        RateResponseMovies rate = assertCallSucceeds(rest.movie.postRate(movieID, guestSessionID, moviesRateBody)).body();
        //Validate the movie rating was sent correctly
        Assert.assertEquals(rate.getStatusMessage(), "Success.",
                "Movie rating cannot be submitted");
    }

    @Test
    /*
    Emulates a user that wants to delete a given rating to an specific movie.
     */
    public void movies_rateMovie_DeleteRatingMovieSuccessfully() {
        double ratingValue = 9.5;
        String searchQuery = "Gremlins";
        //Creating a guest session
        String guestSessionID = getGuestSessionID();
        //Searching for movie's id.
        String movieID = getMovieID(searchQuery);
        //Creating request body for rating movies
        MoviesRateBody moviesRateBody = new MoviesRateBody(ratingValue);
        assertCallSucceeds(rest.movie.postRate(movieID, guestSessionID, moviesRateBody));
        DeleateRatingResponseMovies deleteRating = assertCallSucceeds(rest.movie.deleteLRatingtMovie(movieID, guestSessionID)).body();
        //Validate the movie rating was deleted correctly
        Assert.assertEquals(deleteRating.getStatusMessage(), "The item/record was deleted successfully.",
                "Delete rating cannot be submitted");
    }

    //TODO: Create more test scenarios for searching movies

    @Test(enabled = false)
    public void movies_movieCredits_VerifyActorIncludedInKnownForMoviesCast() {
        /*
        Code
        Verify that all the movie results obtained by an actor search includes the actor in the cast
         */
    }

    @Test(enabled = false)
    public void movies_nowPlaying_MovieIsNotInTheatres() {
        /*
        This test should validate that a movie is not listed on theatres
         */
    }

    @Test(enabled = false)
    public void movies_discover_FilteringIsWorking() {
        /*
        Verify that getting any certification requested from "/certification/movie/list"
        works at dicover movies feature at least 1 result must be obtained
         */
    }

    @Test(enabled = false)
    public void search_duplicatedMovieNames_VerifyMovieSIDAreDifferent() {
        /*
        Isle of dogs has 2 search result with exact same name, its going to be necessary to validate
        that at least two or more movies wth the same name has differente id's.
         */
    }

}