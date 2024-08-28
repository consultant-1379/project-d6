package com.ericsson.retrospectivetool.model;

import javax.persistence.*;

@Entity
@Table(name="items")
public class ItemModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String author, teamName, description, mood;

    private boolean priority;

    public ItemModel(){

    }

    public ItemModel( String author, String teamName, String description, String mood, boolean priority){
        //this.id = id;
        this.author=author;
        this.teamName=teamName;
        this.description=description;
        this.mood=mood;
        this.priority=priority;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public boolean getPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }
}
