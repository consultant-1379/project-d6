package com.ericsson.retrospectivetool.controller;

import com.ericsson.retrospectivetool.model.ItemCommentModel;
import com.ericsson.retrospectivetool.model.ItemModel;
import com.ericsson.retrospectivetool.repository.ItemRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@CrossOrigin(origins={"http://localhost:8080"})
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemRepositoryInterface repository;

    @GetMapping(produces={"application/json","application/xml"})
    public ResponseEntity<Collection<ItemModel>> getAllMembers() {
        Collection<ItemModel> result = repository.getNodes();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<ItemModel> insertNode(@RequestBody ItemModel n) {
        int id = repository.insertNode(n);
        n.setId(id);
        URI uri = URI.create("/item/" + n.getMood() + "/" + id);
        return ResponseEntity.created(uri).body(n);
    }

    @PostMapping("/comment")
    public ResponseEntity<ItemCommentModel> insertComment(@RequestBody ItemCommentModel n) {
        int id = repository.insertComment(n);
        n.setId(id);
        URI uri = URI.create("/item/comment");
        return ResponseEntity.created(uri).body(n);
    }

    @GetMapping("/comment/sad")
    public ResponseEntity<Collection<ItemCommentModel>> getCommentSad() {
        Collection<ItemCommentModel> result = repository.getCommentSad();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/comment/glad")
    public ResponseEntity<Collection<ItemCommentModel>> getCommentGlad() {
        Collection<ItemCommentModel> result = repository.getCommentGlad();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/comment/mad")
    public ResponseEntity<Collection<ItemCommentModel>> getCommentMad() {
        Collection<ItemCommentModel> result = repository.getCommentMad();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/sad")
    public ResponseEntity<Collection<ItemModel>> getSadMood() {
        Collection<ItemModel> result = repository.getSad();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/mad")
    public ResponseEntity<Collection<ItemModel>> getMadMood() {
        Collection<ItemModel> result = repository.getMad();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/glad")
    public ResponseEntity<Collection<ItemModel>> getGladMood() {
        Collection<ItemModel> result = repository.getGlad();
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping
    public ResponseEntity deleteAllMoods() {
        repository.deleteMoods();
        return ResponseEntity.ok().build();
    }
}
