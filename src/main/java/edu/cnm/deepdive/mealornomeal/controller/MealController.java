package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Calendar;
import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import edu.cnm.deepdive.mealornomeal.model.entity.User;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import edu.cnm.deepdive.mealornomeal.model.service.UserRepository;
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
@RequestMapping("/meals")
@ExposesResourceFor(Meal.class)
public class MealController {

  private final MealRepository mealRepository;
  private final UserRepository userRepository;

  @Autowired
  public MealController(MealRepository mealRepository,
      UserRepository userRepository) {
    this.mealRepository = mealRepository;
    this.userRepository = userRepository;
  }

  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Meal get(@PathVariable long id) {
    return mealRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Meal> getMeals() {return mealRepository.getAllByOrderByCreator_IdAsc();}


  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Meal> search(@RequestParam(name = "q", required = true) String filter) {
    return mealRepository.getAllByNameContainingOrderByNameAsc(filter);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Meal> postCreator(@RequestBody Meal meal) {
    if (meal.getCreator() != null && meal.getCreator().getId() != null) {
      meal.setCreator(userRepository.findById(
              meal.getCreator().getId()
          ).orElseThrow(NoSuchElementException::new));
    }
    return ResponseEntity.created(meal.getHref()).body(meal);
  }


  @PutMapping(value = "/{id:\\d+}",
  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Meal putName(@PathVariable long id, @RequestBody Meal meal) {
    Meal existingMeal = get(id);
    if (meal.getName() != null) {
      existingMeal.setName(meal.getName());
    }
    return mealRepository.save(existingMeal);
  }

  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Meal putInstruction(@PathVariable long id, @RequestBody Meal meal) {
    Meal existingMeal = get(id);
    if (meal.getInstruction() != null) {
      existingMeal.setInstruction(meal.getInstruction());
    }
    return mealRepository.save(existingMeal);
  }

  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Meal putPrepTime(@PathVariable long id, @RequestBody Meal meal) {
    Meal existingMeal = get(id);
    if (meal.getPrepTime() != null) {
      existingMeal.setPrepTime(meal.getPrepTime());
    }
    return mealRepository.save(existingMeal);
  }

  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Meal putRequirements(@PathVariable long id, @RequestBody Meal meal) {
    Meal existingMeal = get(id);
    if (meal.getRequirements() != null) {
      existingMeal.setRequirements(meal.getRequirements());
    }
    return mealRepository.save(existingMeal);
  }

  // TODO set Calendar meal slots containing deleted meal to null
  @DeleteMapping(value = "/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id) {
    mealRepository.delete(get(id));
  }

  }
