package com.back;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(@Qualifier("personRepositoryV2") PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public int count() {
        return personRepository.count();
    }
}