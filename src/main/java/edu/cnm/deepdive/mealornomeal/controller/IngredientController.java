package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.service.IngredientRepository;
import edu.cnm.deepdive.mealornomeal.model.service.ListRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Ingredient Controller controls Ingredient entity. These are the individual food components
 * which  are used to create a meal.
 */

@RestController
@RequestMapping("/meals/{mealId:\\d+}/ingredients")
@ExposesResourceFor(Ingredient.class)
public class IngredientController {

  /**
   * Ingredient Controller utilizes the Ingredient repository to load ingredients for meal entities.
   * @param - Takes a ingredientRepository parameter
   */
  private final IngredientRepository ingredientRepository;
  private final MealRepository mealRepository;

  @Autowired
  public IngredientController(IngredientRepository ingredientRepository,
      MealRepository mealRepository, ListRepository listRepository){
    this.ingredientRepository = ingredientRepository;
    this.mealRepository = mealRepository;
  }

  /**
   * Allows the user to get a specific Ingredient entity based on Id.
   * @param id The Primary Key
   * @return returns the Ingredient with the specified Id.
   */
  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Ingredient get(@PathVariable long mealId, @PathVariable long id) {
    return ingredientRepository.findById(id).orElseThrow((NoSuchElementException::new));
  }

  /**
   * Searches for Ingredients and sorts name in ascending order
   * @param filter - String input provided by user
   * @return - All Ingredients that fit that filter
   */
  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Ingredient> search(@RequestParam(name = "q", required = true) String filter) {
    return ingredientRepository.getAllByNameOrderByNameAsc(filter);
  }

  /**
   * Gets the values for Ingredient name and Quantity based on Ingredient's Primary Key
   * @param id - The Ingredient's Primary Key and identifier
   * @return - Returns the Ingredient name and Quantity for an existing Ingredient
   */
//   TODO verify if this is OK
//  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public String getAmount(@PathVariable long id) {
//    Ingredient existingIngredient = get(id);
//    return existingIngredient.getQuantity();
//  }

  // TODO Update our ERD to reflect changes between Meal and Ingredient... and possibly Calendar
  // TODO Determine if this belongs in the ListItem or Meal class instead
//  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public Iterable<Meal> getIngredients(@PathVariable long id) {
//    return mealRepository.findById(id)
//        .map(ingredientRepository::)
//  }

  /**
   * Returns the Quantity of the Ingredient entity based on Id input.
   * @param mealId - Id associated with the meal
   * @param ingredient - The body of the Ingredient entity
   * @return - Returns the current Ingredient entity with the newly updated Quantity
   */
  @PutMapping(value = "/{id:\\d+}",
  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Ingredient putQuantity(@PathVariable long mealId, @RequestBody Ingredient ingredient) {
    Ingredient existingIngredient = get(mealId, ingredient.getId());
    if (ingredient.getQuantity() != null && ingredient.getId() != null){
      existingIngredient.setQuantity(ingredient.getQuantity());
    }
    return ingredientRepository.save(ingredient);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Ingredient> post(@PathVariable long mealId, @RequestBody Ingredient ingredient) {
    mealRepository.findById(mealId)
        .map((meal) -> {
          meal.getIngredients().add(ingredient);
          mealRepository.save(meal);
          return ingredient;
        })
        .orElseThrow(NoSuchElementException::new);
    return ResponseEntity.created(ingredient.getHref()).body(ingredient);
  }

}
