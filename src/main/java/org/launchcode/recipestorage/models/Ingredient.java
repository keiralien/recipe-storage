package org.launchcode.recipestorage.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient extends AbstractEntity {

    @NotNull
    @DecimalMin("0.0")
    private double amount;

    @ManyToOne
    private Recipe recipe;

    @ManyToMany
    private List<Unit> units;

    public Ingredient() {}

    public Ingredient(double amount, List<Unit> units) {
        this.amount = amount;
        this.units = units;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
