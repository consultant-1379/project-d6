package com.ericsson.retrospectivetool.controller;

import com.ericsson.retrospectivetool.model.TeamModel;
import com.ericsson.retrospectivetool.repository.TeamRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@CrossOrigin(origins={"http://localhost:8080"})
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamRepositoryInterface repository;

    @GetMapping(produces={"application/json","application/xml"})
    public ResponseEntity<Collection<TeamModel>> getAllNodes() {
        Collection<TeamModel> result = repository.getNodes();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<TeamModel> insertNode(@RequestBody TeamModel n) {
        int id = repository.insertNode(n);
        n.setId(id);
        URI uri = URI.create("/team/" + id);
        return ResponseEntity.created(uri).body(n);
    }

    @DeleteMapping
    public ResponseEntity deleteAllTeams() {
        repository.deleteTeams();
        return ResponseEntity.ok().build();
    }
}
