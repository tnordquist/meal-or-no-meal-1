package edu.cnm.deepdive.mealornomeal.controller.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import java.util.Date;
import org.springframework.lang.NonNull;

@JsonPropertyOrder(value = {"id", "name", "href"})
public interface FlatMeal {

  Long getId();

  @NonNull
  String getName();

  URI getHref();

}
