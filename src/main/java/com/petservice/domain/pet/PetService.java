package com.petservice.domain.pet;

import java.util.List;

/**
 * Created by Joe Deluca on 9/22/2016.
 */
public interface PetService {
    List<Pet> findAll(String keyword, PetType petType);
    Pet findOne(Long id);
    Pet save(Pet pet);
    void delete(Long id);
}
