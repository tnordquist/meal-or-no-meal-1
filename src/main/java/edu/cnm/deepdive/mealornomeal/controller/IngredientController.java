package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import edu.cnm.deepdive.mealornomeal.model.service.IngredientRepository;
import edu.cnm.deepdive.mealornomeal.model.service.ListRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ingredient Controller utilize the Ingredient repository to load ingredients for meal entities.
 *
 */

@RestController
@RequestMapping("/ingredients")
@ExposesResourceFor(Ingredient.class)
public class IngredientController {

  /**
  * Ingredient Controller utilizes the Ingredient repository to load ingredients for meal entities.
   *

   */
  private final IngredientRepository ingredientRepository;

  @Autowired
  public IngredientController(IngredientRepository ingredientRepository,
      MealRepository mealRepository, ListRepository listRepository){
    this.ingredientRepository = ingredientRepository;
  }

  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Ingredient get(@PathVariable long id) {
    return ingredientRepository.findById(id).orElseThrow((NoSuchElementException::new));
  }
  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Ingredient> search(@RequestParam(name = "q", required = true) String filter) {
    return ingredientRepository.getAllByNameContainingOrderByNameAsc(filter);
  }

  // TODO verify if this is OK
  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getAmount(@PathVariable long id) {
    Ingredient existingIngredient = get(id);
    return existingIngredient.getQuantity();
  }

  // TODO Determine if this belongs in the ListItem or Meal class instead
//  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public Iterable<Meal> getIngredients(@PathVariable long id) {
//    return mealRepository.findById(id)
//        .map(ingredientRepository::)
//  }


  @PutMapping(value = "/{id:\\d+}",
  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Ingredient putQuantity(@PathVariable long id, @RequestBody Ingredient ingredient) {
    Ingredient existingIngredient = get(id);
    if (ingredient.getQuantity() != null && ingredient.getId() != null){
      existingIngredient.setQuantity(ingredient.getQuantity());
    }
    return ingredientRepository.save(ingredient);
  }

}
