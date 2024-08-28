package com.ericsson.retrospectivetool.controller;

import com.ericsson.retrospectivetool.model.PersonModel;
import com.ericsson.retrospectivetool.repository.PersonRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@CrossOrigin(origins={"http://localhost:8080"})
@RequestMapping("/members")
public class PersonController {

    @Autowired
    private PersonRepositoryInterface repository;

    @GetMapping(produces={"application/json","application/xml"})
    public ResponseEntity<Collection<PersonModel>> getAllMembers() {
        Collection<PersonModel> result = repository.getMembers();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<PersonModel> insertMember(@RequestBody PersonModel n) {
        int id = repository.insertMember(n);
        n.setId(id);
        URI uri = URI.create("/members/" + id);
        return ResponseEntity.created(uri).body(n);
    }

    @DeleteMapping
    public ResponseEntity deleteAllMembbers() {
        repository.deleteMembers();
        return ResponseEntity.ok().build();
    }
}
