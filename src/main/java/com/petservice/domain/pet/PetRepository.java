package com.petservice.domain.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Joe Deluca on 9/21/2016.
 */
public interface PetRepository extends JpaRepository<Pet, Long>, JpaSpecificationExecutor {
}
