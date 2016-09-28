package com.petservice.domain.pet;

import com.petservice.domain.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
@Entity
public class Pet extends DomainEntity {

    public Pet() {
        // Just until we have photo upload
        setPhotoUrl("http://www.freedigitalphotos.net/images/img/homepage/87357.jpg");
    }

    @NotNull
    @Size(min = 3, max = 50)
    @Column
    private String title;

    @NotNull
    @Size(min = 5, max = 256)
    @Column
    private String description;

    @NotNull
    @Column
    private PetType petType;

    @Column
    private String photoUrl;

    @NotNull
    @Min(1)
    @Max(10000)
    @Column
    private double purchasePrice;

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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
