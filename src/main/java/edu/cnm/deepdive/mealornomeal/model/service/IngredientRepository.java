package edu.cnm.deepdive.mealornomeal.model.service;

import edu.cnm.deepdive.mealornomeal.model.entity.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This Repository gathers all requested data for Ingredients and returns the items organized as per
 * individual Query.
 */
public interface IngredientRepository extends JpaRepository <Ingredient, Long> {

  /**
   * This @Query organizes the searched Ingredients by Name and organizes them by name in ascending
   * order as a String.
   * @param name
   * @return String of Ingredients by Name
   */
  @Query
  Iterable<Ingredient> getAllByNameContainingOrderByNameAsc(String name);

}
