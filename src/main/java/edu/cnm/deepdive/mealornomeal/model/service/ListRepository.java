package edu.cnm.deepdive.mealornomeal.model.service;

import edu.cnm.deepdive.mealornomeal.model.entity.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListRepository extends JpaRepository <ListItem, Long>{

    @Query
    Iterable<ListItem> getAllByNameContainingOrderByNameAsc(String name);

}
