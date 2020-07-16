package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.User;
import edu.cnm.deepdive.mealornomeal.model.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The User service is the service to log in with google.
 */


@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
public class UserServiceController {

  private final UserService userService;

  /**
   *This Method with the @AutoWired annotation removes the properties element in the XML.
   * @param userService
   */

  @Autowired
  public UserServiceController(UserService userService) {this.userService = userService;}
  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(@PathVariable long id) {
    return ResponseEntity.of(userService.get(id));
  }

  /**
   * GetMapping
   * @param auth
   * @return
   */

  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(Authentication auth) {
    User user = (auth != null) ? (User) auth.getPrincipal() : null;
    return ResponseEntity.of(Optional.ofNullable(user));
  }
}


