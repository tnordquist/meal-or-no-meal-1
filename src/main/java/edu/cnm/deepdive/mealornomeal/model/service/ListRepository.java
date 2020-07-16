package edu.cnm.deepdive.mealornomeal.model.service;

import edu.cnm.deepdive.mealornomeal.model.entity.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This Repository gathers all requested data for ListItems and returns the items organized as per
 * individual Query.
 */
public interface ListRepository extends JpaRepository <ListItem, Long>{

    /**
     *This @Query organizes the searched ListItems by Name and organizes them by name in ascending
     * order as a String.
     * @param name
     * @return String of ListItems by Name
     */
    @Query
    Iterable<ListItem> getAllByNameContainingOrderByNameAsc(String name);

}
