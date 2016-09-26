package com.petservice.controllers.dtos;

import com.petservice.domain.pet.Pet;
import com.petservice.domain.pet.PetType;

/**
 * Created by joseph.deluca of the House Targaryen, the first of his name. Coder of the Seven Kingdoms of Leonardo and Protector of the Realm, The Unburnt, Mother of Dragons, Breaker of Chains and Khaleesi of the Great Grass Sea.
 * I created this file on 26/09/2016.
 */
public class PetDto {
    private Long id;
    private String title;
    private String description;
    private PetType petType;
    private String photoUrl;
    private double purchasePrice;
    private int version = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
