package com.petservice.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseph.deluca of the House Targaryen, the first of his name. Coder of the Seven Kingdoms of Leonardo and Protector of the Realm, The Unburnt, Mother of Dragons, Breaker of Chains and Khaleesi of the Great Grass Sea.
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
