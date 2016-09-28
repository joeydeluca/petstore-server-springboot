package com.petservice.controllers;

import com.petservice.controllers.exceptions.ValidationException;
import com.petservice.domain.pet.Pet;
import com.petservice.domain.pet.PetService;
import com.petservice.domain.pet.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
@RestController
@RequestMapping("/pets")
public class PetController {

    private PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Pet>> findMany(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "petType", required = false) String petType) {

        List<Pet> pets = petService.findAll(keyword, PetType.fromString(petType));

        return ResponseEntity.ok(pets);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Pet> findOne(@PathVariable Long id) {

        Pet pet = petService.findOne(id);
        if(pet == null) {
            throw new IllegalArgumentException();
        }

        return ResponseEntity.ok(pet);
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Pet> create(@Valid @RequestBody Pet pet, BindingResult bindingResult) {

        validateEntity(bindingResult);

        Pet savedPet = petService.save(pet);

        return ResponseEntity.ok(savedPet);
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Pet> update(@Valid @RequestBody Pet pet, BindingResult bindingResult) {

        if(pet.getId() == null) {
            throw new IllegalArgumentException();
        }

        validateEntity(bindingResult);

        Pet savedPet = petService.save(pet);

        return ResponseEntity.ok(savedPet);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        petService.delete(id);

        return ResponseEntity.ok().build();
    }

    /**
     * Execute validations
     * @param bindingResult
     * @throws ValidationException
     */
    private void validateEntity(BindingResult bindingResult) throws ValidationException {
        if(bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                errors.add(String.format("%s %s", ((FieldError)objectError).getField(), objectError.getDefaultMessage()));
            }
            throw new ValidationException(errors);
        }
    }

}
