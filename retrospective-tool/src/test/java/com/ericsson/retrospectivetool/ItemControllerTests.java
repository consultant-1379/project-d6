package com.ericsson.retrospectivetool;

import com.ericsson.retrospectivetool.controller.ItemController;
import com.ericsson.retrospectivetool.model.ItemCommentModel;
import com.ericsson.retrospectivetool.model.ItemModel;
import com.ericsson.retrospectivetool.model.PersonModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ItemControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testThatPostCreatesMember() throws URISyntaxException {
        ItemModel person = new ItemModel("Jill", "Test Team", "sad test", "sad", false);
        URI uri = new URI("http://localhost:9090/item/");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ItemModel> request = new HttpEntity<>(person, headers);

        ResponseEntity<ItemModel> result = restTemplate.postForEntity(uri, request, ItemModel.class);
        System.out.println(result.toString());
        assertEquals("Correct status code returned" ,201, result.getStatusCodeValue());
    }

    @Test
    public void testThatPostCommentCreatesNewComment() throws URISyntaxException {
        ItemCommentModel comment = new ItemCommentModel("Taylor", "Comment Team", "sad comment test", "sad comment");
        URI uri = new URI("http://localhost:9090/item/comment");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ItemCommentModel> request = new HttpEntity<>(comment, headers);

        ResponseEntity<ItemCommentModel> result = restTemplate.postForEntity(uri, request, ItemCommentModel.class);
        System.out.println(result.toString());
        assertEquals("Correct status code returned" ,201, result.getStatusCodeValue());
    }

    @Test
    public void testThatGetAllSadItemsReturnsItemsFull() {
        ResponseEntity<List<ItemModel>> responseEntity = restTemplate.exchange("/item/sad",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemModel>>() {
                });
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testThatGetAllMadItemsReturnsItemsFull() {
        ResponseEntity<List<ItemModel>> responseEntity = restTemplate.exchange("/item/mad",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemModel>>() {
                });
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testThatGetAllGladItemsReturnsItemsFull() {
        ResponseEntity<List<ItemModel>> responseEntity = restTemplate.exchange("/item/glad",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemModel>>() {
                });
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testThatGetAllSadCommentsReturnsItemsFull() {
        ResponseEntity<List<ItemModel>> responseEntity = restTemplate.exchange("/item/comment/sad",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemModel>>() {
                });
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testThatGetAllMadCommentsReturnsItemsFull() {
        ResponseEntity<List<ItemModel>> responseEntity = restTemplate.exchange("/item/comment/mad",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemModel>>() {
                });
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testThatGetAllGladCommentsReturnsItemsFull() {
        ResponseEntity<List<ItemModel>> responseEntity = restTemplate.exchange("/item/comment/glad",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ItemModel>>() {
                });
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testThatDeleteItemsWorks(){
        restTemplate.delete("http://localhost:9090/team/");
        Collection<ItemModel> items = restTemplate.getForObject("http://localhost:9090/team/", Collection.class);
        assertEquals("Number of teams in collection", 0, items.size());
    }
}
