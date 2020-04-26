package org.launchcode.recipestorage.models;

import javax.persistence.*;

@Entity
public class Directions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="recipe.id")
    private Recipe recipe;

    private String instruction;

    public Directions() {}

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
