package edu.cnm.deepdive.mealornomeal.model.service;

import edu.cnm.deepdive.mealornomeal.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User,Long> {

  @Query
  Iterable<User> getAllByNameContainingOrderByNameAsc(Long id);

  Optional<User> findFirstByOauthkey(String oauthKey);
}
