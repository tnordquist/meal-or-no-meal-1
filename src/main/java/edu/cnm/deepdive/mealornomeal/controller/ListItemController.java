package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Ingredient;
import edu.cnm.deepdive.mealornomeal.model.entity.ListItem;
import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import edu.cnm.deepdive.mealornomeal.model.service.IngredientRepository;
import edu.cnm.deepdive.mealornomeal.model.service.ListRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("list_items")
@ExposesResourceFor(ListItem.class)
public class ListItemController {

  private final ListRepository listRepository;

  @Autowired
  public ListItemController(
      IngredientRepository ingredientRepository, ListRepository listRepository) {

    this.listRepository = listRepository;
  }

  @GetMapping(value = "/{id://d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ListItem get(@PathVariable long id) {
    return listRepository.findById(id)
        .orElseThrow(NoSuchElementException::new);
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<ListItem> search(@RequestParam(name = "q", required = true) String filter) {
    return listRepository.getAllByNameContainingOrderByNameAsc(filter);
  }

  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getAmount(@PathVariable long id) {
    ListItem existingListItem = get(id);
    return existingListItem.getQuantity();
  }

  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ListItem putQuantity(@PathVariable long id, @RequestBody ListItem listItem) {
    ListItem existingListItem = get(id);
    if (listItem.getQuantity() != null && listItem.getId() != null) {
      existingListItem.setQuantity(listItem.getListItem());
    }
    return listRepository.save(listItem);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListItem> postName(@RequestBody ListItem listItem) {
    if (listItem.getName() != null) {
      listItem.setName(listRepository.findById(listItem.getId()
      ).orElseThrow(NoSuchElementException::new));
    }
    return ResponseEntity.created(listItem.getHref()).body(listItem);
  }

  @DeleteMapping(value = "/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id) {
    listRepository.delete(get(id));
  }

}


