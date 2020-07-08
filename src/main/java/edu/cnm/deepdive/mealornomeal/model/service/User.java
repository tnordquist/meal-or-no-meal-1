package edu.cnm.deepdive.mealornomeal.model.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface User extends JpaRepository<User,Long> {

  @Query
  Iterable<User> getAllByNameContainingOrderByNameAsc(String name);

}
