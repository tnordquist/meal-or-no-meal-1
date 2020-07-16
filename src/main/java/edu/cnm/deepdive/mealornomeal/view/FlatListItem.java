package edu.cnm.deepdive.mealornomeal.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import org.springframework.lang.NonNull;

/**
 * FlatListItem interface allows ListItemRepository to access abstract methods getId, getName,
 * getQuantity, getHref.
 */
//TODO determin if name should be removed or added below
  @JsonPropertyOrder(value = {"id", "name", "quantity", "href"})
  public interface FlatListItem {

  /**
   * @return - Returns a ListItem Id of type Long
   */
    Long getId();

  /**
   * @return - Returns a ListItem of type String
   */
    @NonNull
    String getListItem();

  /**
   * @return - Returns the base URL of an external resource
   */
    URI getHref();

  }

