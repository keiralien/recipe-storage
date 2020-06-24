package org.launchcode.recipestorage.models.data;

import org.launchcode.recipestorage.models.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    long count();
    Page<Recipe> findAll(Pageable pageable);
    List<Recipe> findByCategories_Id(int categoryId);
}
