package org.launchcode.recipestorage.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends AbstractEntity{

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany(mappedBy = "categories")
    private List<Recipe> recipes = new ArrayList<>();

    public Category () {}

    public int getId() {
        return id;
    }
}
