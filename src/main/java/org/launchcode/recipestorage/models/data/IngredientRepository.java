package org.launchcode.recipestorage.models.data;

import org.launchcode.recipestorage.models.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
