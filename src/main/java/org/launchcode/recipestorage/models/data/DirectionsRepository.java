package org.launchcode.recipestorage.models.data;

import org.launchcode.recipestorage.models.Directions;
import org.springframework.data.repository.CrudRepository;

public interface DirectionsRepository extends CrudRepository <Directions, Integer> {
}
