package org.launchcode.recipestorage.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe extends AbstractEntity{

    @Id
    @GeneratedValue
    private int id;

    @Size(max = 250, message = "Description must be less than 250 characters.")
    private String description;

    @ManyToMany
    @NotNull(message = "Select at least one category.")
    private List<Category> categories;

    @OneToMany(mappedBy = "recipe")
    private List<Directions> directions = new ArrayList<>();

    public Recipe() {}

    public Recipe(String description, String directions) {
        this.description = description;
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
}
