package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.ListItem;
import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("list_items")
@ExposesResourceFor(ListItem.class)
public class ListItemController {

  private final IngredientRepository ingredientRepository;
  private final ListRepository listRepository;
  private final MealRepository mealRepository;

  @Autowired
  public ListItemController(
      IngredientRepository ingredientRepository, ListRepository listRepository ) {
    this.ingredientRepository = ingredientRepository;
    this.listRepository = listRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<ListItem> get () {return listRepository.getAllByNameContainingOrderByNameAsc();
  }

  @GetMapping(value = "/{list_item_id://d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ListItem get(@PathVariable long list_item_id) {
    return listRepository.findById(list_item_id)
        .orElseThrow(NoSuchElementException::new);
  }

  @GetMapping(value = "/{list_item_id://d+}/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Meal> getMeals(@PathVariable long list_item_id){
  return listRepository.findById(list_item_id)
    .map(ingredientRepository::getAllByNameContainingOrderByNameAsc)
      .orElseThrow(NoSuchElementException::new);
  }
}


