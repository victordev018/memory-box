package com.jota.memorybox.service;

import com.jota.memorybox.model.PostMemory;
import com.jota.memorybox.repository.PostMemoryRepository;
import com.jota.memorybox.service.exception.PostMemoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostMemoryService {

    // dependencies

    @Autowired
    private PostMemoryRepository repository;

    public PostMemory findById(Long id){
        Optional<PostMemory> personOptional = repository.findById(id);
        return personOptional.orElseThrow(() -> new PostMemoryNotFoundException("Person not found!"));
    }

    public PostMemory findByName(String name){
        Optional<PostMemory> personOptional = repository.findByName(name);
        return personOptional.orElseThrow(() -> new PostMemoryNotFoundException("Person not found!"));
    }

    public void create(PostMemory obj){
        repository.save(obj);
    }

    public List<PostMemory> findAll(){
        return repository.findAll();
    }

    public PostMemory update(String name, PostMemory person){
        PostMemory obj = findByName(name);
        obj.setName(person.getName());
        obj.setImageUrl(person.getImageUrl());
        obj.setDescription(person.getDescription());
        return repository.save(obj);
    }

}
