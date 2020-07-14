package edu.cnm.deepdive.mealornomeal.model.service;

import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository <Meal, Long>{

  Iterable<Meal> getAllByNameContainingOrderByNameAsc(String name);

  Iterable<Meal> getAllByOrderByCreator_IdAsc();

}
