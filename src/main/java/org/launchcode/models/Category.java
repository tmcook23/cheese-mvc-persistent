package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    // Part 2: Add Cheeses to Category
    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Cheese> cheeses = new ArrayList<>();


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
