package edu.cnm.deepdive.mealornomeal.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import org.springframework.lang.NonNull;

/**
 * FlatIngredient interface allows IngredientRepository to access abstract methods getId, getName,
 * getQuantity, getHref.
 */
  @JsonPropertyOrder(value = {"id", "name", "quantity", "href"})
  public interface FlatIngredient {

  /**
   * @return - Returns a Ingredient Id of type Long
   */
    Long getId();

  /**
   * @return - Returns a Ingredient of type String
   */
    @NonNull
    String getName();

  /**
   * @return - Returns a Quantity of an Ingredient of type String
   */
    String getQuantity();

  /**
   * @return - Returns the base URL of an external resource
   */
    URI getHref();

  }
