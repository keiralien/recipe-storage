package org.launchcode.recipestorage.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;

@Entity
public class Unit extends AbstractEntity{

    @NotBlank(message = "Select a unit.")
    private String abbreviation;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Ingredient ingredient;

    public Unit () {}

    public Unit (String abbreviation, Ingredient ingredient) {
        this.abbreviation = abbreviation;
        this.ingredient = ingredient;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
