package org.launchcode.recipestorage.models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Entity
public class Ingredient extends AbstractEntity {

    @NotNull
    @DecimalMin("0.0")
    private double amount;

    @OneToOne
    private Unit unit;

    @ManyToOne
    @JoinColumn(name="recipe.id")
    private Recipe recipe;

    public Ingredient() {}

    public Ingredient(double amount, Unit unit, Recipe recipe) {
        this.amount = amount;
        this.unit = unit;
        this.recipe = recipe;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
