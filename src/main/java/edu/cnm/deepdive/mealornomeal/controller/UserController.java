package edu.cnm.deepdive.mealornomeal.controller;


import edu.cnm.deepdive.mealornomeal.model.entity.User;
import edu.cnm.deepdive.mealornomeal.model.service.CalendarRepository;
import edu.cnm.deepdive.mealornomeal.model.service.ListRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import edu.cnm.deepdive.mealornomeal.model.service.UserRepository;
import java.awt.PageAttributes.MediaType;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<User> get() {
    return userRepository.getAllByNameContainingOrderByNameAsc();
  }

  @GetMapping(value ="/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable long id ) {
    return userRepository.findById(id).orElseThrow(NoSuchFieldException::new);
  }


}

