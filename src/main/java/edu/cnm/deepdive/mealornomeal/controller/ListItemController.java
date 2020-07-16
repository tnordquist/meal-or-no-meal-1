package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.ListItem;
import edu.cnm.deepdive.mealornomeal.model.service.IngredientRepository;
import edu.cnm.deepdive.mealornomeal.model.service.ListRepository;
import edu.cnm.deepdive.mealornomeal.model.service.UserRepository;
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

/**
 * The ListItemController controls the details of a ListItem that
 * populates the shopping list.
 */
@RestController
@RequestMapping("list_items")
@ExposesResourceFor(ListItem.class)
public class ListItemController {

  private final ListRepository listRepository;
  private final UserRepository userRepository;

  /**
   * a constructor that creates a contextualized instances or list and user
   * repositories.
   * @param ingredientRepository - Takes an ingredientRepository paramenter
   * @param listRepository - Takes a listRepository parameter
   * @param userRepository - Takes userRepository parameter
   */

  @Autowired
  public ListItemController(
      IngredientRepository ingredientRepository, ListRepository listRepository,
      UserRepository userRepository) {
    this.listRepository = listRepository;
    this.userRepository = userRepository;
  }

  /**
   * Finds a ListItem based on the Primary Key
   * @param id - The ListItem's Primary Key
   * @return - Returns the List Item
   */
  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ListItem get(@PathVariable long id) {
    return listRepository.findById(id)
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * Allows for a Search of ListItem by name and returns ListItems in Ascending Order by Name
   * @param filter a String provided by the User
   * @return filtered ListItems by Name
   */
  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<ListItem> search(@RequestParam(name = "q", required = true) String filter) {
    return listRepository.getAllByNameContainingOrderByNameAsc(filter);
  }

  /**
   * Returns the Quantity of the Ingredient entity based on Id query.
   * @param id - Id associated with the ingredient
   * @return - Returns the current ListItem entity with the newly updated Quantity
   */
  //TODO repetitive.
//  @GetMapping(value = "{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public String getAmount(@PathVariable long id) {
//    ListItem existingListItem = get(id);
//    return existingListItem.getQuantity();
//  }

  /**
   * Returns the Quantity of the ListItem entity based on Id input.
   * @param id - Id associated with the ListItem
   * @param listItem - Id associated with the ListItem
   * @return Quantity of the ListItem searched
   */
  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ListItem putQuantity(@PathVariable long id, @RequestBody ListItem listItem) {
    ListItem existingListItem = get(id);
    if (listItem.getQuantity() != null && listItem.getId() != null) {
      existingListItem.setQuantity(listItem.getListItem());
    }
    return listRepository.save(listItem);
  }

  /**
   * Allows the user to POST a ListItem entity which includes Name and Quantity.
   * @param listItem - The body of the ListItem entity
   * @return - The just created ListItem
   */
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListItem> postName(@RequestBody ListItem listItem) {
    if (listItem.getUser_id() != null) {
      listItem.setUser_id(userRepository.findById(
          listItem.getUser_id().getId()
      ).orElseThrow(NoSuchElementException::new));
    }
    return ResponseEntity.created(listItem.getHref()).body(listItem);
  }
  /**
   * Allows the user to delete a ListItem they have created
   * @param id - ID associated with the specific ListItem they would like to delete
   */
  @DeleteMapping(value = "/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id) {
    listRepository.delete(get(id));
  }

}


