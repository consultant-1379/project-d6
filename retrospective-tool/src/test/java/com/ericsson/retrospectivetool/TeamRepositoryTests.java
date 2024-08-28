package com.ericsson.retrospectivetool;

import com.ericsson.retrospectivetool.model.TeamModel;
import com.ericsson.retrospectivetool.repository.TeamRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.util.List;

@SpringBootTest
@Sql({"/populate_teams_db.sql"})
public class TeamRepositoryTests {

    @Autowired
    TeamRepositoryInterface repository;

    @Test
    void testFindAllNodes() {
        assertThat(repository.getNodes().size(), is(2));
    }


    @Test
    public void testInsertAndGet(){
        int id = repository.insertNode(new TeamModel("team A"));
        List<TeamModel> teams = (List<TeamModel>) repository.getNodes();
        assertEquals("Number of teams is 1", 3, teams.size());
        assertEquals("Team id is correct", id, teams.get(2).getId()); //wrong
        assertEquals("Team name is correct", "team A", teams.get(2).getName());
    }

    @Test
    public void testDeleteTeams(){
        repository.deleteTeams();
        assertEquals("Number of teams", 0, repository.getNodes().size());
    }

}
