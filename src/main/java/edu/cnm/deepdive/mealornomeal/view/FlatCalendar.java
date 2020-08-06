package edu.cnm.deepdive.mealornomeal.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.cnm.deepdive.mealornomeal.model.entity.Calendar.MealSlot;
import java.net.URI;
import java.time.LocalDate;
import org.springframework.lang.NonNull;

/**
 * The FlatCalendar used for situation where there are one to many, many to one, many to many relations.
 */

public interface FlatCalendar {

  @JsonPropertyOrder(value = {"id", "name", "href"})

    Long getId();

    LocalDate getDate();

    MealSlot getMealSlot();

    URI getHref();

  }
