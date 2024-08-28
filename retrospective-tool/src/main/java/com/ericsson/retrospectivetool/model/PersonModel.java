package com.ericsson.retrospectivetool.model;

import javax.persistence.*;

@Entity
@Table(name="members")
public class PersonModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name, teamName, email;

    public PersonModel(){

    }

    public PersonModel(/*int id,*/ String name, String teamName, String email){
        //this.id = id;
        this.name=name;
        this.teamName=teamName;
        this.email=email;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

}
