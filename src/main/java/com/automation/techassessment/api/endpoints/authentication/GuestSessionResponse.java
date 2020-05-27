package com.automation.techassessment.api.endpoints.authentication;

import com.automation.techassessment.api.model.TMDbObject;

public class GuestSessionResponse implements TMDbObject {
    private String guest_session_id;

    public String getGuestSessionID() {
        return guest_session_id;
    }
}
