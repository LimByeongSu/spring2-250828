package com.back;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

//
    public int count() {
        return personRepository.count();
    }
}