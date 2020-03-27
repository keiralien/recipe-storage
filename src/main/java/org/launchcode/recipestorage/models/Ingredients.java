package org.launchcode.recipestorage.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Ingredients {

    @Id
    @GeneratedValue
    private int id;
}
