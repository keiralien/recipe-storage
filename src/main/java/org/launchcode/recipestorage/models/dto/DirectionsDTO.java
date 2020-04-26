package org.launchcode.recipestorage.models.dto;

import org.launchcode.recipestorage.models.Directions;

import java.util.List;

public class DirectionsDTO {
    private List<Directions> directionsList;

    public void addDirections(Directions directions) {
        this.directionsList.add(directions);
    }

    public List<Directions> getDirectionsList() {
        return directionsList;
    }

    public void setDirectionsList(List<Directions> directionsList) {
        this.directionsList = directionsList;
    }
}
