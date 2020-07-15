package edu.cnm.deepdive.mealornomeal.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id, name, oauthKey "})
public interface FlatUser {

  Long getId();
  
  String getName();

  String getOauthKey();

}
