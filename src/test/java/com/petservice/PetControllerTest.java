package com.petservice;

import com.petservice.domain.pet.Pet;
import com.petservice.domain.pet.PetType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
public class PetControllerTest extends AbstractIntegrationTest {

    @Test
    public void shouldNotSaveBadPetData1() {
        adminLogin();
        Pet pet = new Pet();
        pet.setDescription("not so cool pet");
        ResponseEntity<Pet> responseEntity = getRestTemplate().exchange("/pets", HttpMethod.POST, new HttpEntity<>(getAuthHeaders()), Pet.class, pet);
        Assert.assertFalse(HttpStatus.PRECONDITION_FAILED.equals(responseEntity.getStatusCode()));
    }

    @Test()
    public void shouldNotSaveBadPetData2() {
        adminLogin();
        Pet pet = new Pet();
        pet.setPurchasePrice(999999);
        ResponseEntity<Pet> responseEntity = getRestTemplate().exchange("/pets", HttpMethod.POST, new HttpEntity<>(getAuthHeaders()), Pet.class, pet);
        Assert.assertFalse(HttpStatus.PRECONDITION_FAILED.equals(responseEntity.getStatusCode()));
    }

    @Test()
    public void shouldNotCreatePetWhenNotLoggedIn() {
        Pet pet = getValidPetStub();
        ResponseEntity<Pet> responseEntity = getRestTemplate().exchange("/pets", HttpMethod.POST, new HttpEntity<>(getAuthHeaders()), Pet.class, pet);
        Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(responseEntity.getStatusCode()));
    }

    @Test()
    public void shouldNotUpdatePetWhenNotAdmin() {
        Pet pet = getValidPetStub();
        pet.setId(new Long(1));
        ResponseEntity<Pet> responseEntity = getRestTemplate().exchange("/pets", HttpMethod.PUT, new HttpEntity<>(getAuthHeaders()), Pet.class, pet);
        Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(responseEntity.getStatusCode()));
    }

    @Test()
    public void shouldNotDeletePetWhenNotAdmin() {
        ResponseEntity<Pet> responseEntity = getRestTemplate().exchange("/pets/1", HttpMethod.DELETE, new HttpEntity<>(getAuthHeaders()), Pet.class);
        Assert.assertTrue(HttpStatus.UNAUTHORIZED.equals(responseEntity.getStatusCode()));
    }

    @Test
    public void shouldSavePet() {
        adminLogin();

        Pet originalPet = getValidPetStub();

        ResponseEntity<Pet> responseEntity = getRestTemplate().exchange("/pets", HttpMethod.POST, getSecureRequest(originalPet), Pet.class, originalPet);
        Assert.assertTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()));
        Pet savedPet = responseEntity.getBody();

        Assert.assertTrue(savedPet != null);
        Assert.assertTrue(savedPet.getTitle().equals(originalPet.getTitle()));
        Assert.assertTrue(savedPet.getDescription().equals(originalPet.getDescription()));
        Assert.assertTrue(savedPet.getPhotoUrl().equals(originalPet.getPhotoUrl()));
        Assert.assertTrue(savedPet.getPetType().equals(originalPet.getPetType()));
        Assert.assertTrue(savedPet.getPurchasePrice() == originalPet.getPurchasePrice());
    }

    @Test
    public void shouldUpdatePet() {
        adminLogin();

        Pet originalPet = getValidPetStub();
        originalPet.setId(new Long(1));
        originalPet.setDescription("joeyisthecoolestguyintheworld");

        ResponseEntity<Pet> responseEntity = getRestTemplate().exchange("/pets", HttpMethod.PUT, getSecureRequest(originalPet), Pet.class, originalPet);
        Assert.assertTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()));
        Pet savedPet = responseEntity.getBody();

        Assert.assertTrue(savedPet != null);
        Assert.assertTrue(savedPet.getDescription().equals(originalPet.getDescription()));
    }

    @Test
    public void shouldDeletePet() {
        adminLogin();
        ResponseEntity<String> responseEntity = getRestTemplate().getForEntity("/pets/1", String.class, HttpMethod.DELETE);
        Assert.assertTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()));
    }

    @Test
    public void shouldSearchforPets() {
        ResponseEntity<List> responseEntity = getRestTemplate().getForEntity("/pets", List.class, HttpMethod.GET);
        Assert.assertTrue(HttpStatus.OK.equals(responseEntity.getStatusCode()));
    }

    private Pet getValidPetStub() {
        Pet pet = new Pet();
        pet.setTitle("title");
        pet.setDescription("cool pet");
        pet.setPetType(PetType.DOG);
        pet.setPurchasePrice(12);
        return pet;
    }

}