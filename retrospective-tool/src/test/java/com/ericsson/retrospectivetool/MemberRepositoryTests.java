package com.ericsson.retrospectivetool;

import com.ericsson.retrospectivetool.model.PersonModel;
import com.ericsson.retrospectivetool.repository.PersonRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private PersonRepositoryInterface repository;

    @Test
    public void testInsertRepository(){
        int sizeBefore = repository.getMembers().size();
        repository.insertMember(new PersonModel());
        int sizeAfter = repository.getMembers().size();
        assertEquals("Size of repository increased by one", sizeBefore+1, sizeAfter);
    }

    @Test
    public void testGetMember(){
        int id = repository.insertMember(new PersonModel("Jim", "Team A", "jim@jim.com"));
        PersonModel person = repository.getMember(id);
        assertEquals("Person has same name", "Jim", person.getName());
        assertEquals("Person has same team name", "Team A", person.getTeamName());
        assertEquals("Person has same email", "jim@jim.com", person.getEmail());
    }

    @Test
    public void testDeleteMembers(){
        repository.deleteMembers();
        assertEquals("Number of teams", 0, repository.getMembers().size());
    }

}
