package com.petservice.controllers;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.NoResultException;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
@ControllerAdvice
public class RestErrorHandler {

    @ExceptionHandler(
            {
                    NoResultException.class,
                    EmptyResultDataAccessException.class
            }
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse notFoundHandler(Exception e){
        return new ErrorResponse(HttpStatus.NOT_FOUND, "Whatever you are looking for does not exist. " + e.getMessage());
    }


    /**
     * For all other unhandled exceptions.
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ErrorResponse handleResourceException(Exception e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}
