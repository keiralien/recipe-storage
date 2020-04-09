package org.launchcode.recipestorage.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Unit extends AbstractEntity {

    private String abbreviation;

    @ManyToMany(mappedBy = "units")
    private List<Ingredient> ingredients = new ArrayList<>();

    public Unit () {}

    public Unit (String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
