package com.bookstore.bookstoreapi.model;

public class Author {
    private int id;
    private String name;
    private String biography;

    // Default constructor
    public Author() {}

    // Parameterized constructor
    public Author(int id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }

    // Getters for get the value 
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }

    //Setters for change the value 
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
    
}
