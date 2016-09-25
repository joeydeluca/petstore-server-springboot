package com.petservice;

import com.petservice.domain.pet.Pet;
import com.petservice.domain.pet.PetRepository;
import com.petservice.domain.pet.PetType;
import com.petservice.domain.user.Role;
import com.petservice.domain.user.User;
import com.petservice.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class PetServiceApplication implements CommandLineRunner {

    @Autowired
    private Environment environment;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PetServiceApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        // Add initial user accounts
        userRepository.save(new User("admin", "admin", Role.ROLE_ADMIN));
        userRepository.save(new User("guest", "guest", Role.ROLE_GUEST));

        // Add some test data if we are not in prod
        if(!Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            Stream.iterate(0, i -> i + 1).limit(10).forEach(i -> {
                Pet pet = new Pet();
                pet.setTitle("Cool Pet " + i);
                pet.setDescription("this is a really cool pet description. blah blah blah blah " + i);
                pet.setPetType(PetType.DOG);
                pet.setPhotoUrl("http://www.freedigitalphotos.net/images/img/homepage/87357.jpg");
                pet.setPurchasePrice(i);
                petRepository.save(pet);
            });

        }
    }
}
