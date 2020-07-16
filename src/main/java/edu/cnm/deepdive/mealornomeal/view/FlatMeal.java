package edu.cnm.deepdive.mealornomeal.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import org.springframework.lang.NonNull;

/**
 * FlatMeal interface allows MealRepository to access abstract methods getId, getName, getHref.
 */

@JsonPropertyOrder(value = {"id", "name", "href"})
public interface FlatMeal {

  /**
   * @return - Returns a Meal ID of type Long
   */
  Long getId();

  /**
   * @return - Returns a Meal Name of type String
   */
  @NonNull
  String getName();

  /**
   * @return - Returns the base URL of an external resource
   */
  URI getHref();

}
