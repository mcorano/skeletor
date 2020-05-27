package com.automation.techassessment.api.endpoints.authentication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthenticationEndpoint {
    String BASE_URL = "authentication";

    @GET(BASE_URL + "/guest_session/new")
    Call<GuestSessionResponse> createGuestSession();
}