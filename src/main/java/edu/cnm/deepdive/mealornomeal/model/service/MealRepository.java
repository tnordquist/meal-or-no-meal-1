package edu.cnm.deepdive.mealornomeal.model.service;

/**
 *
 */

import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository <Meal, Long>{

  /**
   *
   * @param filter
   * @return
   */
  Iterable<Meal> getAllByNameContainingOrderByNameAsc(String filter);

  /**
   *
   * @return
   */
  Iterable<Meal> getAllByOrderByCreator_IdAsc();

}
