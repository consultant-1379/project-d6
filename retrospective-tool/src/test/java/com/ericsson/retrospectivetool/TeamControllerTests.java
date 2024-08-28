package com.ericsson.retrospectivetool;

import com.ericsson.retrospectivetool.controller.TeamController;
import com.ericsson.retrospectivetool.model.ItemModel;
import com.ericsson.retrospectivetool.model.PersonModel;
import com.ericsson.retrospectivetool.model.TeamModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TeamControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testThatPostCreatesTeam() throws URISyntaxException {
        TeamModel team = new TeamModel("test team");
        URI uri = new URI("http://localhost:9090/team/");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<TeamModel> request = new HttpEntity<>(team, headers);

        ResponseEntity<TeamModel> result = restTemplate.postForEntity(uri, request, TeamModel.class);

        assertEquals("Correct status code returned" ,201, result.getStatusCodeValue());
    }

    @Test
    public void testThatGetAllItemsReturnsItemsFull() {
        ResponseEntity<List<TeamModel>> responseEntity = restTemplate.exchange("/team",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TeamModel>>() {
                });
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void testThatDeleteRemovesAllTeams(){
        restTemplate.delete("http://localhost:9090/item/");
        Collection<ItemModel> items = restTemplate.getForObject("http://localhost:9090/item/", Collection.class);
        assertEquals("Number of items in collection", 0, items.size());
    }
}
