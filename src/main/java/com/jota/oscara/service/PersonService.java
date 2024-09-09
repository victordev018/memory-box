package com.jota.oscara.service;

import com.jota.oscara.model.Person;
import com.jota.oscara.repository.PersonRepository;
import com.jota.oscara.service.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    // dependencies

    @Autowired
    private PersonRepository repository;

    public Person findById(Long id){
        Optional<Person> personOptional = repository.findById(id);
        return personOptional.orElseThrow(() -> new PersonNotFoundException("Person not found!"));
    }

    public Person findByName(String name){
        Optional<Person> personOptional = repository.findByName(name);
        return personOptional.orElseThrow(() -> new PersonNotFoundException("Person not found!"));
    }

    public void create(Person obj){
        repository.save(obj);
    }

    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person update(String name, Person person){
        Person obj = findByName(name);
        obj.setName(person.getName());
        obj.setImageUrl(person.getImageUrl());
        obj.setDescription(person.getDescription());
        return repository.save(obj);
    }

}
