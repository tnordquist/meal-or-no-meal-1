package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Calendar;
import edu.cnm.deepdive.mealornomeal.model.service.CalendarRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import edu.cnm.deepdive.mealornomeal.model.service.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Calendar Controller controls what calendar is being used and retains meals that have been
 * added int the meal slots via Meal Controller/Repository.
 * Below it is also declaring constants that will be used.
 */

@RestController
@RequestMapping("/calendar")
@ExposesResourceFor(Calendar.class)
public class CalendarController {

  private final MealRepository mealRepository;
  private final CalendarRepository calendarRepository;
  private final UserRepository userRepository;

  /**
   * This @Autowire declares the controller parameters and, and also declares no instances.
   * @param calendarRepository
   * @param mealRepository
   * @param userRepository
   */
  @Autowired
  public CalendarController(CalendarRepository calendarRepository,
      MealRepository mealRepository, UserRepository userRepository) {
    this.calendarRepository = calendarRepository;
    this.mealRepository = mealRepository;
    this.userRepository = userRepository;
  }


  /**
   * This @Get method returns existing calendars.
   * @param id
   * @return calendarRepository
   */
  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Calendar get(@PathVariable long id, Authentication auth) {
    return calendarRepository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  /**
   * This @Get allows to search calendars by name if one has been shared by other users.
   * @param filter
   * @return calendarRepository
   */
//  @GetMapping(value = "/search", params = {"q"}, produces = MediaType.APPLICATION_JSON_VALUE)
//  public Iterable<Calendar> searchByName(@RequestParam(name = "q", required = true) String filter) {
//    return calendarRepository.getAllByNameOrderByDateAsc(filter);
//  }

  /**
   * This @Get method allows user to search for a created calendar by date.
   * @param from
   * @param to
   * @return calendarRepository
   */

  @GetMapping(value = "/search", params = {"from", "to"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Calendar> searchByDate(@RequestParam(required = true) LocalDate from, @RequestParam (required = true) LocalDate to, Authentication auth) {
    return calendarRepository.getAllByDateBetweenOrderByDate(from, to);
  }


  /**
   * This @PostMapping allows user to create a new calendar with saved meal slots.
   *
   * @param calendar
   * @return ResponseEntity.created(calendar.getHref ()).body(calendar)
   */

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Calendar> postCalendarId(@RequestBody Calendar calendar, Authentication auth) {
    if (calendar.getMealSlot() != null && calendar.getMealSlot() != null) {
      calendar.setCreator(userRepository.findById(
          calendar.getCreator().getId()
      ).orElseThrow(NoSuchElementException::new));
    }
    return ResponseEntity.created(calendar.getHref()).body(calendar);
  }

  /**
   * This @PutMapping allows the creator of a calendar to edit the name of their calendar.
   *
   * @param id
   * @param calendar
   * @return
   */

  @PutMapping(value = "/{id:\\d+}",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Calendar put(@PathVariable long id, @RequestBody Calendar calendar, Authentication auth) {
    Calendar existingCalendar = get(id, auth);
    if (calendar.getId() != null) {
      existingCalendar.setId(calendar.getId());
    }
    return calendarRepository.save(existingCalendar);
  }

  /**
   * This @Delete method allows the creator of a calendar to delete it.
   *
   * @param id
   */
  @DeleteMapping(value = "/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id, Authentication auth) {
    calendarRepository.delete(get(id, auth));
  }

// TODO Figure out how to write a DELETE method to delete a meal from a calendar meal slot.
}
