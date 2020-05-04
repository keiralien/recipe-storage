package org.launchcode.recipestorage.models;

import javax.persistence.*;

@Entity
public class Directions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="recipe.id")
    private Recipe recipe;

    private String instruction;

    public Directions() {}

    public Directions (Recipe recipe, String instruction) {
        this.recipe = recipe;
        this.instruction = instruction;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return instruction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directions that = (Directions) o;
        return instruction == that.instruction;
    }

}
