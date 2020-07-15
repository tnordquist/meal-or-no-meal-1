package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.service.IngredientRepository;
import edu.cnm.deepdive.mealornomeal.model.service.ListRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
@ExposesResourceFor(Ingredient.class)
public class IngredientController {

  /**
  * Ingredient Controller utilizes the Ingredient repository to load ingredients for meal entities.
   *

   */
  private final IngredientRepository ingredientRepository;
  private final MealRepository mealRepository;
  private final ListRepository listRepository;

  @Autowired
  public IngredientController(IngredientRepository ingredientRepository,
      MealRepository mealRepository, ListRepository listRepository){
    this.ingredientRepository = ingredientRepository;
    this.mealRepository = mealRepository;
    this.listRepository = listRepository;
  }

//  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//  public Iterable<Ingredient> get() {
//    return ingredientRepository.getAllByNameContainingOrderByNameAsc();}

//  @PostMapping(
//      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<Ingredient> post(@RequestBody Ingredient ingredient, Authentication
//  )

  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Ingredient get(@PathVariable long id) {
    return ingredientRepository.findById(id).orElseThrow((NoSuchElementException::new));
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Ingredient> search(@RequestParam(name = "q", required = true) String filter){
    return ingredientRepository.getAllByNameContainingOrderByNameAsc(filter);
  }

//  @PutMapping(value = "/id:\\d+}/listItem",
//  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public Ingredient putListItem(@PathVariable long id, @RequestBody ListItem listItem) {
//    Ingredient ingredient = get(id);
//    if (listItem != null && ingredient.getId() != null){
//      listItem = listRepository.findById(listItem.getId()).orElseThrow((NoSuchElementException::new));
//    }
//    ingredient.setListItem(listItem);
//    return ingredientRepository.save(ingredient);
//  }

}
