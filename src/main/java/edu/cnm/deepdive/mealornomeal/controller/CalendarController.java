package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Calendar;
import edu.cnm.deepdive.mealornomeal.model.service.CalendarRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import edu.cnm.deepdive.mealornomeal.model.service.UserRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendar")
@ExposesResourceFor(Calendar.class)
public class CalendarController{

  private final MealRepository mealRepository;
  private final CalendarRepository calendarRepository;
  private final UserRepository userRepository;


  @Autowired
  public CalendarController(CalendarRepository calendarRepository,
      MealRepository mealRepository, UserRepository userRepository) {
    this.calendarRepository = calendarRepository;
    this.mealRepository = mealRepository;
    this.userRepository = userRepository;
  }



  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Calendar get(@PathVariable long id) {
    return calendarRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Calendar> searchByName(@RequestParam(name = "q", required = true) String filter) {
    return calendarRepository.getAllByNameOrderByDateAsc(filter);
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Calendar> searchByDate(@RequestParam(name = "q", required = true) String filter) {
    return calendarRepository.getAllByDateOrderByDateAsc(filter);
  }

  @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Calendar> searchMealId(@RequestParam(name = "q", required = true) String filter) {
    return calendarRepository.getAllById(filter);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Calendar> postMealSlot(@RequestBody Calendar calendar, Authentication auth) {
    if (calendar.getMeal() != null && calendar.getMeal().getId() != null) {
      calendar.setMeal(
          mealRepository.findById(
              calendar.getMeal().getId()
          ).orElseThrow(NoSuchElementException::new));
    }
    calendarRepository.save(calendar);
    return ResponseEntity.created(calendar.getHref()).body(calendar);
  }


  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Calendar> postCalendarId(@RequestBody Calendar calendar) {
    if (calendar.getMealSlot() != null && calendar.getMealSlot() != null) {
      calendar.setCreator(userRepository.findById(
          calendar.getCreator().getId()
      ).orElseThrow(NoSuchElementException::new));
    }
    return ResponseEntity.created(calendar.getHref()).body(calendar);
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

  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Calendar putMealSlot(@PathVariable long id, @RequestBody Calendar mealSlot) {
    Calendar existingCalendar = get(id);
    if (mealSlot.getId() != null) {
      existingCalendar.setMealSlot(mealSlot.getMealSlot());
    }
    return calendarRepository.save(existingCalendar);
  }

//  @PutMapping(value = "/{id:\\d+}",
//      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//  public Calendar putMealId(@PathVariable long id, @RequestBody Calendar mealId) {
//    Calendar existingCalendar = get(id);
//    if (mealId.getId() != null) {
//      existingCalendar.setMealSlot(mealId.getName());
//    }
//    return calendarRepository.save(existingCalendar);
//  }

}
