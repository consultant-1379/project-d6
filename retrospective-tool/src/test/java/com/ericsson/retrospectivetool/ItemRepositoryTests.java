package com.ericsson.retrospectivetool;

import com.ericsson.retrospectivetool.model.ItemCommentModel;
import com.ericsson.retrospectivetool.model.ItemModel;
import com.ericsson.retrospectivetool.model.TeamModel;
import com.ericsson.retrospectivetool.repository.ItemRepositoryInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
//@Sql({"/populate_items_db.sql"})
public class ItemRepositoryTests {


    @Autowired
    ItemRepositoryInterface repository;


    @Test
    public void testInsertAndGetSad(){
        repository.deleteMoods();
        int id = repository.insertNode(new ItemModel("Karen", "Team B", "Not working", "Sad", false));
        List<ItemModel> items = (List<ItemModel>) repository.getSad();
        assertEquals("Number of sad items is 1", 1, items.size());
        assertEquals("Item id is correct", id, items.get(0).getId());
        assertEquals("Item team is correct", "Team B", items.get(0).getTeamName());
    }

    @Test
    public void testInsertAndGetMad(){
        repository.deleteMoods();
        int id = repository.insertNode(new ItemModel("Karen", "Team A", "Not working", "Mad", false));
        List<ItemModel> items = (List<ItemModel>) repository.getMad();
        assertEquals("Number of mad items is 1", 1, items.size());
        assertEquals("Item id is correct", id, items.get(0).getId());
        assertEquals("Item team is correct", "Team A", items.get(0).getTeamName());
    }

    @Test
    public void testInsertAndGetGlad(){
        repository.deleteMoods();
        int id = repository.insertNode(new ItemModel("Karen", "Team C", "Not working", "Glad", false));
        List<ItemModel> items = (List<ItemModel>) repository.getGlad();
        assertEquals("Number of glad items is 1", 1, items.size());
        assertEquals("Item id is correct", id, items.get(0).getId());
        assertEquals("Item team is correct", "Team C", items.get(0).getTeamName());
    }

    @Test
    public void testInsertAndGetAllItems(){
        repository.deleteMoods();
        repository.insertNode(new ItemModel("Karen", "Team B", "Not working", "Sad", false));
        repository.insertNode(new ItemModel("Warren", "Team B", "test", "Mad", false));
        repository.insertNode(new ItemModel("Katie", "Team B", "test 2", "Glad", false));
        List<ItemModel> items = (List<ItemModel>) repository.getNodes();
        assertEquals("Number of items", 3, items.size());
    }

    @Test
    public void testDeleteItems(){
        boolean delete = repository.deleteMoods();
        assertEquals("items were deleted", true, delete);
        assertEquals("Number of items is 0", 0, repository.getNodes().size());
        assertEquals("Number of comments is 0", 0, repository.getCommentSad().size()+repository.getCommentGlad().size()+repository.getCommentMad().size());
    }

    @Test
    public void testInsertAndGetSadComment(){
        repository.insertComment(new ItemCommentModel("John", "item b", "sad", "sad comment"));
        List<ItemCommentModel> itemComments = (List<ItemCommentModel>) repository.getCommentSad();
        assertEquals("Number of sad comments", 1, itemComments.size());
    }

    @Test
    public void testInsertAndGetMadComment(){
        repository.insertComment(new ItemCommentModel("John", "item b", "mad", "mad comment"));
        List<ItemCommentModel> itemComments = (List<ItemCommentModel>) repository.getCommentMad();
        assertEquals("Number of mad comments", 1, itemComments.size());
    }

    @Test
    public void testInsertAndGetGladComment(){
        repository.insertComment(new ItemCommentModel("John", "item b", "glad", "glad comment"));
        List<ItemCommentModel> itemComments = (List<ItemCommentModel>) repository.getCommentGlad();
        assertEquals("Number of glad comments", 1, itemComments.size());
    }

}
