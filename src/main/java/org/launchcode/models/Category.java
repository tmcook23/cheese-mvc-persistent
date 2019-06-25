package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Category {


    // Create a private id field that's an int.
    @Id
    @GeneratedValue
    private int id;


    // Create a private name property that's a string.
    @NotNull
    @Size(min=3, max=15)
    private String name;


    // Default (no-argument) constructor, used by Hibernate:
    public Category() { }

    // Constructor that accepts a parameter to set name:
    public Category(String name) {
        this.name = name;
    }


    // Add a public getter for id.
    public int getId() {
        return id;
    }

    // Add a public getter and setter for name.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
