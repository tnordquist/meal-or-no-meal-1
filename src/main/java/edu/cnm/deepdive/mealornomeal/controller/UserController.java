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
  private final MealRepository mealRepository;
  private final ListRepository listRepository;
  private final CalendarRepository calendarRepository;

  public UserController(UserRepository userRepository, MealRepository mealRepository, ListRepository listRepository, CalendarRepository calendarRepository) {
    this.userRepository = userRepository;
        this.mealRepository = mealRepository;
        this.listRepository = listRepository;
        this.calendarRepository = calendarRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<User> get() {
    return userRepository.getAllByNameContainingOrderByNameAsc();
  }

  @GetMapping(value ="/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable long id ) {
    return userRepository.findById(id).orElseThrow(NoSuchFieldException::new);
  }

  @PutMapping(value = "{id:\\d+}",
      consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public User put(@PathVariable long id, @RequestBody User user) {
    User existingUser = get(id);
    if (user.getName() != null) {
      existingUser.setName(user.getName());
    }
    if (user. != 0) {
      existingUser.setSkillLevel(user.getSkillLevel());
    }
    return userRepository.save(existingUser);


}

