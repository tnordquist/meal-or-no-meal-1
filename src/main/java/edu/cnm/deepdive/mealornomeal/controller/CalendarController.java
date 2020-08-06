package edu.cnm.deepdive.mealornomeal.controller;

import edu.cnm.deepdive.mealornomeal.model.entity.Calendar;
import edu.cnm.deepdive.mealornomeal.model.entity.Meal;
import edu.cnm.deepdive.mealornomeal.model.service.CalendarRepository;
import edu.cnm.deepdive.mealornomeal.model.service.MealRepository;
import edu.cnm.deepdive.mealornomeal.model.service.UserService;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;
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
@RequestMapping("/calendars")
@ExposesResourceFor(Calendar.class)
public class CalendarController {

  private final MealRepository mealRepository;
  private final CalendarRepository calendarRepository;
  private final UserService userService;

  /**
   * This @Autowire declares the controller parameters and, and also declares no instances.
   * @param calendarRepository
   * @param mealRepository
   * @param userService
   */
  @Autowired
  public CalendarController(CalendarRepository calendarRepository,
      MealRepository mealRepository,
      UserService userService) {
    this.calendarRepository = calendarRepository;
    this.mealRepository = mealRepository;
    this.userService = userService;
  }


  /**
   * This @Get method returns existing calendars.
   * @param id
   * @return calendarRepository
   */
  @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Calendar get(@PathVariable long id, Authentication auth) {
    return userService.get(auth)
        .flatMap((user) ->
            calendarRepository.findById(id)
            .map((calendar) -> {
              userService.requireAccess(user, calendar.getCreator());
              return calendar;
            })
        )
        .orElseThrow(NoSuchElementException::new);
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
    return userService.get(auth)
        .map((user) -> calendarRepository.getAllByCreatorAndDateBetweenOrderByDate(user, from, to))
        .orElseThrow(NoSuchElementException::new);
  }


  /**
   * This @PostMapping allows user to create a new calendar with saved meal slots.
   *
   * @param calendar
   * @return ResponseEntity.created(calendar.getHref ()).body(calendar)
   */
//TODO delcare validation requirements with annotations if we have the time
  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Calendar> postCalendarId(@RequestBody Calendar calendar, Authentication auth) {
    return userService.get(auth)
        .map((user) -> {
          calendar.setCreator(user);
          return user;
        })
        .flatMap((user) -> mealRepository
            .findById((calendar.getMeal() != null) ? calendar.getMeal().getId() : 0))
        .map((meal) -> {
          calendar.setMeal(meal);
          return meal;
        })
        .flatMap((meal) -> Optional.ofNullable(calendar.getMealSlot()))
        .flatMap((mealSlot) -> Optional.ofNullable(calendar.getDate()))
        .map((d) -> {
          calendarRepository.save(calendar);
          return ResponseEntity.created(calendar.getHref()).body(calendar);
        })
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * This @PutMapping allows the creator of a calendar to edit the name of their calendar.
   *
   * @param id
   * @param
   * @return
   */

  @PutMapping(value = "/{id:\\d+}/meal",
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Meal put(@PathVariable long id, @RequestBody Meal meal, Authentication auth) {
    return userService.get(auth)
        .flatMap((user) ->
            calendarRepository.findById(id)
                .map((calendar) -> {
                      userService.requireAccess(user, calendar.getCreator());
                      return calendar;
                    }
                )
                .flatMap((calendar) ->
                    mealRepository.findById(meal.getId())
                        .map((m) -> {
                          calendar.setMeal(m);
                          calendarRepository.save(calendar);
                          return m;
                        })
                )
        )
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * This @Delete method allows the creator of a calendar to delete it.
   *
   * @param id
   */
  @DeleteMapping(value = "/{id:\\d+}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id, Authentication auth) {
    userService.get(auth)
        .flatMap((user) ->
            calendarRepository.findById(id)
                .map((calendar) -> {
                  userService.requireAccess(user, calendar.getCreator());
                  calendarRepository.delete(calendar);
                  return null;
                })
        )
        .orElse(null);
  }

// TODO Figure out how to write a DELETE method to delete a meal from a calendar meal slot.
}
