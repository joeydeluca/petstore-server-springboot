package com.petservice.domain.pet;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
public enum PetType {
    DOG, CAT;

    /**
     * Tries to convert the String input to a PetType
     * @param input
     * @return a PetType or null
     */
    public static PetType fromString(String input) {
        if(input == null) return null;

        for(PetType petType : PetType.values()) {
            if(petType.name().equals(input.toUpperCase())) {
                return petType;
            }
        }

        return null;
    }
}
