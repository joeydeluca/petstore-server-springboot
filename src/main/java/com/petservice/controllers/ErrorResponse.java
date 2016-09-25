package com.petservice.controllers;

import org.springframework.http.HttpStatus;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
public class ErrorResponse {
    public final int statusCode;
    public final String msg;

    public ErrorResponse(HttpStatus statusCode, String msg) {
        this.statusCode = statusCode.value();
        this.msg = msg;
    }


}
