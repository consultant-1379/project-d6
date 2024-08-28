package com.ericsson.retrospectivetool;
/*
import com.ericsson.retrospectivetool.model.Item;
import com.ericsson.retrospectivetool.model.TeamModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class RetroControllerTest {

    private TeamModel team;
    @BeforeEach
    public void setup(){
        team = new TeamModel();
    }
    @Autowired
    private RetroControllerImpl retroController;

    @Test
    public void testAddNewItemToSad(){
        Item item = new Item("Jim", "Test Sad");
        retroController.addRetroToSad(team, item);
        assertEquals("Item added to sad", "Test Sad", retroController.getSadRetros(team, item.getAuthor()).getMessage());
    }

    @Test
    public void testAddNewItemToMad(){
        Item item = new Item("Jim", "Test Mad");
        retroController.addRetroToMad(team, item);
        assertEquals("Item added to sad", "Test Mad", retroController.getMadRetros(team, item.getAuthor()).getMessage());
    }

    @Test
    public void testAddNewItemToGlad(){
        Item item = new Item("Jim", "Test Glad");
        retroController.addRetroToGlad(team, item);
        assertEquals("Item added to sad", "Test Glad", retroController.getGladRetros(team, item.getAuthor()).getMessage());
    }

    @Test
    public void testUpdateSadRetro(){
        Item item = new Item("Jenny", "Test Glad");
        retroController.addRetroToSad(team, item);
        Item newItem = new Item("Jenny", "Test Sad update");
        retroController.updateSadRetro(team, newItem);
        assertEquals("Updated sad item", "Test Sad update", retroController.getSadRetros(team, item.getAuthor()).getMessage());
    }

    @Test
    public void testUpdateMadRetro(){
        Item item = new Item("Jenny", "Test Glad");
        retroController.addRetroToMad(team, item);
        Item newItem = new Item("Jenny", "Test Mad update");
        retroController.updateMadRetro(team, newItem);
        assertEquals("Updated sad item", "Test Mad update", retroController.getMadRetros(team, item.getAuthor()).getMessage());
    }

    @Test
    public void testUpdateGladRetro(){
        Item item = new Item("Jenny", "Test Glad");
        retroController.addRetroToGlad(team, item);
        Item newItem = new Item("Jenny", "Test Glad update");
        retroController.updateGladRetro(team, newItem);
        assertEquals("Updated sad item", "Test Glad update", retroController.getGladRetros(team, item.getAuthor()).getMessage());
    }

    @Test
    public void testDeleteItemFromSadRetro(){
        Item item = new Item("Peter", "Test Sad delete");
        retroController.addRetroToSad(team, item);
        retroController.deleteSadRetro(team, item);
        assertEquals("Deleted Item", null, retroController.getSadRetros(team, item.getAuthor()));
    }

    @Test
    public void testDeleteItemFromMadRetro(){
        Item item = new Item("Peter", "Test Mad delete");
        retroController.addRetroToMad(team, item);
        retroController.deleteMadRetro(team, item);
        assertEquals("Deleted Item", null, retroController.getMadRetros(team, item.getAuthor()));
    }

    @Test
    public void testDeleteItemFromGladRetro(){
        Item item = new Item("Peter", "Test Glad delete");
        retroController.addRetroToGlad(team, item);
        retroController.deleteGladRetro(team, item);
        assertEquals("Deleted Item", null, retroController.getGladRetros(team, item.getAuthor()));
    }
}*/
