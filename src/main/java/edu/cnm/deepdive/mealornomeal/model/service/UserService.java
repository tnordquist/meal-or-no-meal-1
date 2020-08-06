package edu.cnm.deepdive.mealornomeal.model.service;

import edu.cnm.deepdive.mealornomeal.exception.AccessDeniedException;
import edu.cnm.deepdive.mealornomeal.model.entity.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * The user service is a service to connect to goole with the user account.
 */
@Service
public class UserService {

  private final UserRepository userRepository;

  /**
  * the  AutoWired annotation removes the properties element in the XML.
    * Takes an entityLinks parameter.
   * @param
   */

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public synchronized User readOrCreateOne(String oauthKey, String name) {
    return userRepository.findFirstByOauthKey(oauthKey)
        .orElseGet(() -> {
          User user = new User();
          user.setOauthKey(oauthKey);
          user.setName(name);
          return userRepository.save(user);
        });
  }

  /**
   * gets the long id and returns the user Repository by the Id.
   * @param id
   * @return
   */
  public Optional<User> get(Long id) {
    return userRepository.findById(id);
  }

  public Optional<User> get(Authentication auth){
    return Optional.ofNullable((User) auth.getPrincipal());
  }

  public void requireAccess(User current, User required){
   if (required == null || current == null || !current.getId().equals(required.getId())){
     throw new AccessDeniedException();
   }
  }
}
