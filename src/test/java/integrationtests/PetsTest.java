package integrationtests;

import com.petservice.domain.pet.Pet;
import com.petservice.domain.pet.PetRepository;
import com.petservice.domain.pet.PetType;
import com.petservice.security.LoginDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
public class PetsTest extends AbstractIntegrationTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    public void saveNewPet() {
        adminLogin();

        Pet originalPet = new Pet();
        originalPet.setTitle("title");
        originalPet.setDescription("cool pet");
        originalPet.setPetType(PetType.DOG);
        originalPet.setPurchasePrice(12);

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
    public void failToSaveIncompletePet() {
        adminLogin();

        Pet originalPet = new Pet();
        originalPet.setDescription("not so cool pet");

        ResponseEntity<Pet> responseEntity = getRestTemplate().exchange("/pets", HttpMethod.POST, new HttpEntity<>(getAuthHeaders()), Pet.class, originalPet);

        Assert.assertFalse(HttpStatus.OK.equals(responseEntity.getStatusCode()));
    }

}