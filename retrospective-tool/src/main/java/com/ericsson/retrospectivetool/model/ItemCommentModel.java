package com.ericsson.retrospectivetool.model;

import javax.persistence.*;

@Entity
@Table(name="comment")
public class ItemCommentModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String author, item, comment, mood;


    public ItemCommentModel(){

    }

    public ItemCommentModel(String author, String item, String mood, String comment){
        this.author=author;
        this.item=item;
        this.comment=comment;
        this.mood=mood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMood() {
        return mood;
    }

    public void setmood(String mood) {
        this.mood = mood;
    }
}
