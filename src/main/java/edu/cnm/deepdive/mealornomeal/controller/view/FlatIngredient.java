package edu.cnm.deepdive.mealornomeal.controller.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import org.springframework.lang.NonNull;

  @JsonPropertyOrder(value = {"id", "name", "quantity", "href"})
  public interface FlatIngredient {

    Long getId();

    @NonNull
    String getIngredient();

    URI getHref();

  }
