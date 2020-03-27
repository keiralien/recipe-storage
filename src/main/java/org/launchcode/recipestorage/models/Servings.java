package org.launchcode.recipestorage.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Servings {

    @Id
    @GeneratedValue
    private int id;

}
