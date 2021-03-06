package guru.springframework.spring5recipeapp.repositories;


import guru.springframework.spring5recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    // let's assume our description is unique
    Optional<Category> findByDescription(String description);
}
