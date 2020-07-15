package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import edu.cnm.deepdive.mealornomeal.model.service.UserRepository;
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

@RestController
@RequestMapping("/meals")
@ExposesResourceFor(Meal.class)
public class MealController {

  private final MealRepository mealRepository;
  private final UserRepository userRepository;

  @Autowired
  public MealController(MealRepository mealRepository) {
    this.mealRepository = mealRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Meal> get() {return mealRepository.getAllByOrderByCreator_IdAsc();}

  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Meal get(@PathVariable long id) {
    return mealRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Meal> search(@RequestParam(name = "q", required = true) String filter) {
    return mealRepository.getAllByNameContainingOrderByNameAsc(filter);
  }

  // TODO Add authentication piece
  // TODO Set creator to current user
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meal> post(@RequestBody Meal meal) {
    if (meal.getCreator() != null && meal.getCreator().getId() != null) {
      meal.setCreator(userRepository.findById(
              meal.getCreator().getId()
          ).orElseThrow(NoSuchElementException::new));
    }
    return ResponseEntity.created(meal.getHref()).body(meal);
  }

  @PutMapping(value = "/{id:\\d+}",
  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Meal put(@PathVariable long id, @RequestBody Meal meal) {
    Meal existingMeal = get(id);
    if (meal.getName() != null) {
      existingMeal.setName(meal.getName());
    }
    return mealRepository.save(existingMeal);
  }



}
