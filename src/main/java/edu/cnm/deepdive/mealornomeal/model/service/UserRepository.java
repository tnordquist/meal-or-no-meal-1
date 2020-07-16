package edu.cnm.deepdive.mealornomeal.model.service;

import edu.cnm.deepdive.mealornomeal.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The UserRepository gathers all requested data, organizes and displays it per the instructions given.
 */

public interface UserRepository extends JpaRepository<User,Long> {

  /**
   * The Query gets the name in order from the user.
   * @return
   */
  @Query
  Iterable<User> getAllByOrdOrderByNameAsc();

  /**
   * This first find by oauth key looks for the key that is given by the client.
   * @param oauthKey
   * @return
   */
  Optional<User> findFirstByOauthKey(String oauthKey);
}
