package edu.cnm.deepdive.mealornomeal.controller.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.lang.NonNull;

@JsonPropertyOrder({"id, name, oauthKey "})
public interface FlatUser {

  Long getId();

  @NonNull
  String getName();

  String getOauthKey();

}
