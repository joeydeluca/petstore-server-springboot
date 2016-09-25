package com.petservice.domain.pet;

import org.springframework.data.jpa.domain.Specification;

/**
 * Created by Joe Deluca on 9/23/2016.
 */
public class PetSpecification {

    public static Specification<Pet> descriptionContains(String keyword) {
        return (root, query, cb) -> {
            return cb.like(cb.trim(cb.lower(root.get("description"))), "%"+keyword.toLowerCase().trim()+"%");
        };
    }

    public static Specification<Pet> isPetType(PetType petType) {
        return (root, query, cb) -> {
            return cb.equal(root.get("petType"), petType);
        };
    }
}
