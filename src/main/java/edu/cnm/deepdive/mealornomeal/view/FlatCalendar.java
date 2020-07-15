package edu.cnm.deepdive.mealornomeal.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.net.URI;
import org.springframework.lang.NonNull;

public interface FlatCalendar {

  @JsonPropertyOrder(value = {"id", "name", "href"})

    Long getId();

    @NonNull
    String getName();

    URI getHref();

  }
