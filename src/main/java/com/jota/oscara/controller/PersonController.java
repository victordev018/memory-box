package com.jota.oscara.controller;

import com.jota.oscara.dto.PersonRequest;
import com.jota.oscara.dto.PersonResponse;
import com.jota.oscara.model.Person;
import com.jota.oscara.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    // dependencies

    @Autowired
    private PersonService service;

    @GetMapping("/{name}")
    public ResponseEntity<PersonResponse> findByUsername(@PathVariable String name){
        Person person = service.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(new PersonResponse(person.getName(), person.getImageUrl(), person.getDescription()));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody PersonRequest request){
        Person obj = new Person(null, request.name(), request.description(), request.imageUrl());
        service.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> findAll(){
        List<Person> list = service.findAll();
        List<PersonResponse> personResponseList = list.stream()
                .map(obj -> new PersonResponse(obj.getName(), obj.getImageUrl(), obj.getDescription()))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(personResponseList);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<PersonResponse> update(@PathVariable String name, @RequestBody PersonRequest request){
        Person obj = service.update(name, new Person(null, request.name(), request.description(), request.imageUrl()));
        PersonResponse response = new PersonResponse(obj.getName(), obj.getImageUrl(), obj.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
