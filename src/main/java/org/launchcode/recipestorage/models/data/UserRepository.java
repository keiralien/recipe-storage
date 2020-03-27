package org.launchcode.recipestorage.models.data;

import org.launchcode.recipestorage.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
