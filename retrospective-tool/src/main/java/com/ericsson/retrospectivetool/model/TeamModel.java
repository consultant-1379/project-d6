package com.ericsson.retrospectivetool.model;

import javax.persistence.*;

@Entity
@Table(name="team")
public class TeamModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;

    public TeamModel() {

    }

    public TeamModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
