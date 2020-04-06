package org.launchcode.recipestorage.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

public class Recipe extends AbstractEntity{

    @Id
    @GeneratedValue
    private int id;

    @Size(max = 250, message = "Description must be less than 250 characters.")
    private String description;

    private String directions;

    public Recipe() {}

    public Recipe(String name, String description, String directions) {
        this.description = description;
        this.directions = directions;

    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
