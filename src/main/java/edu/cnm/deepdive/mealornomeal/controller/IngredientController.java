package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.exception.AccessDeniedException;
import edu.cnm.deepdive.mealornomeal.model.entity.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.service.IngredientRepository;
import edu.cnm.deepdive.mealornomeal.model.service.ListRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import edu.cnm.deepdive.mealornomeal.model.service.UserService;
import java.time.LocalDate;
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
 * The Ingredient Controller controls Ingredient entity. These are the individual food components
 * which  are used to create a meal.
 */

@RestController
@RequestMapping("/shopping-lists")
@ExposesResourceFor(Ingredient.class)
public class IngredientController {

  /**
   * Ingredient Controller utilizes the Ingredient repository to load ingredients for meal
   * entities.
   *
   * @param - Takes a ingredientRepository parameter
   */
  private final IngredientRepository ingredientRepository;
  private final UserService userService;

  @Autowired
  public IngredientController(IngredientRepository ingredientRepository,
      UserService userService) {
    this.ingredientRepository = ingredientRepository;
    this.userService = userService;
  }

  /**
   * Allows the user to get a specific Ingredient entity based on Id.
   *
   * @param id The Primary Key
   * @return returns the Ingredient with the specified Id.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Ingredient> get(@RequestParam LocalDate start, @RequestParam LocalDate end,
      Authentication auth) {
    return userService.get(auth)
        .map((user) -> ingredientRepository.getAllByDateRange(user.getId(), start, end))
        .orElseThrow(AccessDeniedException::new);
  }

}