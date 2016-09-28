package com.petservice.domain.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe Deluca on 9/22/2016.
 */
@Service
public class PetServiceImpl implements PetService {

    private final String PETS_CACHE = "PETS_CACHE";

    private PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    @Cacheable(value = PETS_CACHE)
    public List<Pet> findAll(String keyword, PetType petType) {

        // setup specifications
        List<Specification<Pet>> specifications = new ArrayList<>();
        if(keyword != null && keyword.length() > 0) {
            specifications.add((PetSpecification.titleContains(keyword)));
        }
        if(petType != null) {
            specifications.add((PetSpecification.isPetType(petType)));
        }

        // Combine all specifications into a super duper specification and search
        if(specifications.size() > 0) {
            Specification<Pet> result = addSpecifications(specifications);
            return petRepository.findAll(result, getPageRequest()).getContent();
        }

        // We are not searching for anything
        return petRepository.findAll(getPageRequest()).getContent();

    }

    @Override
    @Cacheable(PETS_CACHE)
    public Pet findOne(Long id) {
        return petRepository.findOne(id);
    }

    @Override
    @CacheEvict(value=PETS_CACHE, allEntries=true)
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    @CacheEvict(value=PETS_CACHE, allEntries=true)
    public void delete(Long id) {
        petRepository.delete(id);
    }

    // TODO: add pagination
    private PageRequest getPageRequest() {
        return new PageRequest(0, 100, Sort.Direction.DESC, "createdDate");
    }

    private Specification<Pet> addSpecifications(List<Specification<Pet>> specifications) {
        Specification<Pet> result = specifications.get(0);
        for (int i = 1; i < specifications.size(); i++) {
            result = Specifications.where(result).and(specifications.get(i));
        }

        return result;
    }
}
