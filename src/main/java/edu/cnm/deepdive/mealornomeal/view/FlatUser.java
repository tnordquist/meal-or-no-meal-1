package edu.cnm.deepdive.mealornomeal.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.lang.NonNull;

/**
 * The FlatUser is used for situation where there are one to many, many to one, and, many to many relations.
 */

@JsonPropertyOrder({"id, name, oauthKey "})
public interface FlatUser {

  /**
   * @return Returns the User id type long.
   */
  Long getId();

  /**
   * @return Returns the Users name of type String.
   */
  String getName();

  /**
   * @return returns the Users OauthKey of type String.
   */
  String getOauthKey();

}
