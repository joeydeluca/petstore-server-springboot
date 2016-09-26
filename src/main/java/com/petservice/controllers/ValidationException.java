package com.petservice.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseph.deluca
 * I created this file on 26/09/2016.
 */
public class ValidationException extends RuntimeException {
    private List errors = new ArrayList();

    public ValidationException(List errors) {
        this.errors = errors;
    }

    public List getErrors() {
        return errors;
    }

    public void setErrors(List errors) {
        this.errors = errors;
    }
}
