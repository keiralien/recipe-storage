package org.launchcode.recipestorage.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Recipe {

    @Id
    @GeneratedValue
    private int id;

}
