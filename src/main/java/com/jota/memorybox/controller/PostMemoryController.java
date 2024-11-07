package com.jota.memorybox.controller;

import com.jota.memorybox.dto.PostMemoryRequest;
import com.jota.memorybox.dto.PostMemoryResponse;
import com.jota.memorybox.model.PostMemory;
import com.jota.memorybox.service.PostMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostMemoryController {

    // dependencies

    @Autowired
    private PostMemoryService service;

    @GetMapping("/{name}")
    public ResponseEntity<PostMemoryResponse> findByUsername(@PathVariable String name){
        PostMemory person = service.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(new PostMemoryResponse(person.getName(), person.getImageUrl(), person.getDescription()));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody PostMemoryRequest request){
        PostMemory obj = new PostMemory(null, request.name(), request.description(), request.imageUrl());
        service.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PostMemoryResponse>> findAll(){
        List<PostMemory> list = service.findAll();
        List<PostMemoryResponse> personResponseList = list.stream()
                .map(obj -> new PostMemoryResponse(obj.getName(), obj.getImageUrl(), obj.getDescription()))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(personResponseList);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<PostMemoryResponse> update(@PathVariable String name, @RequestBody PostMemoryRequest request){
        PostMemory obj = service.update(name, new PostMemory(null, request.name(), request.description(), request.imageUrl()));
        PostMemoryResponse response = new PostMemoryResponse(obj.getName(), obj.getImageUrl(), obj.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
