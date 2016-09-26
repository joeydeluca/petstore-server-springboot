package com.petservice;

import com.petservice.controllers.dtos.LoginDto;
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
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("admin");

        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/login", loginDto, String.class);

        Assert.assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);

        String authToken  = responseEntity.getBody();
        authToken = authToken.replace("Bearer ", "");

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