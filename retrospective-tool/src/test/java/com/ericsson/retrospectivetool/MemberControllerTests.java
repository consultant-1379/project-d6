package com.ericsson.retrospectivetool;

import com.ericsson.retrospectivetool.model.ItemModel;
import com.ericsson.retrospectivetool.model.PersonModel;
import com.ericsson.retrospectivetool.repository.PersonRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MemberControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testThatPostCreatesMember() throws URISyntaxException {
        PersonModel person = new PersonModel("Jill", "Test Team", "jill@gmail.com");
        URI uri = new URI("http://localhost:9090/members/");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<PersonModel> request = new HttpEntity<>(person, headers);

        ResponseEntity<PersonModel> result = restTemplate.postForEntity(uri, request, PersonModel.class);

        assertEquals("Correct status code returned" ,201, result.getStatusCodeValue());
    }

    @Test
    public void testThatGetAllItemsReturnsItemsFull() {
        ResponseEntity<List<PersonModel>> responseEntity = restTemplate.exchange("/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PersonModel>>() {
                });
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testThatDeleteRemovesAllMembers(){
        restTemplate.delete("http://localhost:9090/members/");
        Collection<ItemModel> items = restTemplate.getForObject("http://localhost:9090/members/", Collection.class);
        assertEquals("Number of items in collection", 0, items.size());
    }

}
