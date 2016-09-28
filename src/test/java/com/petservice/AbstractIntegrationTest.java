package com.petservice;

import com.petservice.controllers.dtos.AuthenticationRequestDto;
import com.petservice.controllers.dtos.AuthenticationResponseDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {PetServiceApplication.class})
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();

    @After
    public void after() {
        headers = new HttpHeaders();
    }

    public void adminLogin() {
        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setUsername("admin");
        authenticationRequestDto.setPassword("admin");

        ResponseEntity<AuthenticationResponseDto> responseEntity = this.restTemplate.postForEntity("/login", authenticationRequestDto, AuthenticationResponseDto.class);

        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);

        String authToken  = responseEntity.getBody().getToken();

        Assert.assertNotNull(authToken);

        headers = new HttpHeaders();
        headers.add("Authorization", authToken);

    }

    public HttpHeaders getAuthHeaders() {
        return headers;
    }

    public TestRestTemplate getRestTemplate() {
        return restTemplate;
    }

    public <T> HttpEntity<T> getSecureRequest(T object) {
        return new HttpEntity<>(object, getAuthHeaders());
    }
}