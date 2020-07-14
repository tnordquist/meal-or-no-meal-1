package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.User;
import edu.cnm.deepdive.mealornomeal.model.service.UserRepository;
import java.util.Optional;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
public class UserController {

  private final UserRepository userRepository;

@Autowired
  public UserController(UserRepository userRepository) {this.userRepository = userRepository;}
  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(@PathVariable long id) {
  return ResponseEntity.of(userRepository.getAllByNameContainingOrderByNameAsc(id));
  }

  @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(Authentication auth) {
  User user = (auth != null) ? (User) auth.getPrincipal() : null;
    return ResponseEntity.of(Optional.ofNullable(user));
  }
}
