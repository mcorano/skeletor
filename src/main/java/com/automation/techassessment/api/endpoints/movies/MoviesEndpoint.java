package com.automation.techassessment.api.endpoints.movies;

import retrofit2.Call;
import retrofit2.http.*;

public interface MoviesEndpoint {

    String BASE_URL = "movie";

    @GET("search/" + BASE_URL)
    Call<SearchResponseMovies> searchMovie(@Query("query") String query);

    @GET(BASE_URL + "/{movieID}/keywords")
    Call<KeywordsResponseMovies> getMovieKeywords(@Path("movieID") String movieID);

    @GET(BASE_URL + "/{movieID}/similar")
    Call<SimilarResponseMovies> getSimilarMovies(@Path("movieID") String movieID);

    @POST(BASE_URL + "/{movieID}/rating")
    Call<RateResponseMovies> postRate(@Path("movieID") String movieID,
                                      @Query("guest_session_id") String guest_session_id,
                                      @Body MoviesRateBody body);

    @POST(BASE_URL + "/{movieID}/rating")
    Call<RateResponseMovies> postRate(@Path("movieID") String movieID);

    @DELETE(BASE_URL + "/{movieID}/rating")
    Call<DeleateRatingResponseMovies> deleteLRatingtMovie(@Path("movieID") String movieID,
                                                          @Query("guest_session_id") String guest_session_id);
}
