package com.automation.techassessment.api.endpoints.movies;

import com.automation.techassessment.api.endpoints.tv.TVResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesEndpoint {

    String BASE_URL = "movie";

    @GET(BASE_URL + "/{movieId}")
    Call<MoviesResponse> getMovieGeneralInfo(@Path("movieId") String movieId);

    @GET(BASE_URL + "/{movieId}/similar")
    Call<MoviesResponse> getSimilarMovies(@Path("movieId") String movieId);

    @GET("search/" + BASE_URL)
    Call<MoviesResponse> searchMovie(@Query("query") String query);

    @GET(BASE_URL + "/latest")
    Call<MoviesResponse> getLatestMovie();

}
