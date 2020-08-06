package edu.cnm.deepdive.mealornomeal.model.service;

import edu.cnm.deepdive.mealornomeal.model.entity.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This Repository gathers all requested data for Ingredients and returns the items organized as per
 * individual Query.
 */
public interface IngredientRepository extends JpaRepository <Ingredient, Long> {

  String DATE_RANGE_QUERY =
      "SELECT i "
          + "FROM Calendar c "
          + "INNER JOIN c.creator u "
          + "INNER JOIN c.meal AS m "
          + "INNER JOIN m.ingredients AS i "
          + "WHERE u.id = :userId "
          + "AND c.date BETWEEN :start AND :end";

  /**
   * This @Query organizes the searched Ingredients by Name and organizes them by name in ascending
   * order as a String.
   * @param name
   * @return String of Ingredients by Name
   */
  Iterable<Ingredient> getAllByNameOrderByNameAsc(String name);

  @Query(value = DATE_RANGE_QUERY)
  Iterable<Ingredient> getAllByDateRange(long userId, LocalDate start, LocalDate end);

}
