package com.petservice.controllers.dtos;

import com.petservice.domain.pet.Pet;

/**
 * Created by joseph.deluca
 * I created this file on 26/09/2016.
 */
public class PetDtoMapper {

    public static PetDto getDto(Pet pet) {
        PetDto dto = new PetDto();
        dto.setId(pet.getId());
        dto.setDescription(pet.getDescription());
        dto.setPetType(pet.getPetType());
        dto.setPhotoUrl(pet.getPhotoUrl());
        dto.setPurchasePrice(pet.getPurchasePrice());
        dto.setTitle(pet.getTitle());
        dto.setVersion(pet.getVersion());
        return dto;
    }

    public static Pet getEntity(PetDto dto) {
        Pet entity = new Pet();
        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setPetType(dto.getPetType());
        entity.setPhotoUrl(dto.getPhotoUrl());
        entity.setPurchasePrice(dto.getPurchasePrice());
        entity.setTitle(dto.getTitle());
        entity.setVersion(dto.getVersion());
        return entity;
    }

}
