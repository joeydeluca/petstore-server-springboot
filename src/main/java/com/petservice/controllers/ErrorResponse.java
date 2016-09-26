package com.petservice.controllers;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
public class ErrorResponse {
    public final int status;
    public final String error;
    public List<String> details = new ArrayList<>();

    public ErrorResponse(HttpStatus statusCode, String msg) {
        this.status = statusCode.value();
        this.error = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }


}
