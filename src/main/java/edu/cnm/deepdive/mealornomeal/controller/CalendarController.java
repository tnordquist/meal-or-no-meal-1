package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Calendar;
import edu.cnm.deepdive.mealornomeal.model.service.CalendarRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import java.util.NoSuchElementException;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
@ExposesResourceFor(Calendar.class)
public class CalendarController{

  private final MealRepository mealRepository;
  private final CalendarRepository calendarRepository;


  @Autowired
  public CalendarController(CalendarRepository calendarRepository,
      MealRepository mealRepository) {
    this.calendarRepository = calendarRepository;
    this.mealRepository = mealRepository;
  }


  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Calendar> post(@RequestBody Calendar calendar, Authentication auth) {
    if (calendar.getMeal() != null && calendar.getMeal().getId() != null) {
      calendar.setMeal(
          mealRepository.findById(
              calendar.getMeal().getId()
          ).orElseThrow(NoSuchElementException::new)
      );
    }
    return ResponseEntity.created(calendar.getHref());
  }

  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Calendar get(@PathVariable long id) {
    return calendarRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Calendar put(@PathVariable long id, @RequestBody Calendar calendar) {
    Calendar existingCalendar = get(id);
    if (calendar.getId() != null) {
      existingCalendar.setId(calendar.getId());
    }
    return calendarRepository.save(existingCalendar);
  }

  @DeleteMapping(value = "/{id:\\d+}")
  public void delete(@PathVariable long id) {

  }


}
