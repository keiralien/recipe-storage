package org.launchcode.recipestorage.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Directions {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Recipe recipe;

    private String instruction;

    public Directions () {}

    public Directions (Recipe recipe, String instruction) {
        this.recipe = recipe;
        this.instruction = instruction;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
