package edu.cnm.deepdive.mealornomeal.model.entity;

import edu.cnm.deepdive.mealornomeal.view.FlatCalendar;
import java.net.URI;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.stereotype.Component;



@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Component
//@Table(
//    indexes = {
//        @Index(columnList = "name")
//    }
//)

/**
 * This Calendar Entity Class declares all of its own attributes along
 * with attributes that are being joined together as foreign keys.
 */

public abstract class Calendar implements FlatCalendar {

  private static EntityLinks entityLinks;

  /**
   * This Column declares the calendar_id attribute and its conditions
   * such as "nullable = false"
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "calendar_id", nullable = false, updatable = false)
  private Long id;

  /**
   * This JoinColumn declares the meal_id attribute and its conditions"
   * It also gives the Calendar entity aces to the Meal Entity attributes"
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "meal_id", nullable = false, updatable = false)
  private Meal meal;

  /**
   * This JoinColumn declares the user_id attribute and its conditions"
   * It also gives the Calendar entity aces to the User Entity attributes"
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User creator;

  /**
   * This Column declares the LocalDate attribute and its conditions
   */
  @Column(nullable = false)
  private LocalDate date;

  /**
   * This Column declares the mealSlot attribute and its conditions
   */
  @Enumerated
  @Column(nullable = false)
  private MealSlot mealSlot;


  /**
   * This Getter gets the id of the calendar.
   */
  public Long getId() {
    return id;
  }

  /**
   * This Getter gets the meal requested.
   */
  public Meal getMeal() {
    return meal;
  }

  /**
   *This Setter sets the meal you've requested.
   */
  public void setMeal(Meal meal) {
    this.meal = meal;
  }

  /**
   *This Getter gets the creator of the calendar.
   */
  public User getCreator() {
    return creator;
  }

  /**
   *This Setter sets the Creator you've requested.
   */
  public void setCreator(User creator) {
    this.creator = creator;
  }

  /**
   * This Getter gets the Date of the calendar.
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   *This Setter sets the date that has been requested.
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  public MealSlot getMealSlot() {
    return mealSlot;
  }

  public void setMealSlot(MealSlot mealSlot) {
    this.mealSlot = mealSlot;
  }

  /**
   *This Setter sets the id of the current calendar.
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This Method with the @AutoWired annotation removes the properties element in the XML.
   * @param entityLinks
   */
  @Autowired
  public static void setEntityLinks(EntityLinks entityLinks) {
    Calendar.entityLinks = entityLinks;
  }

  @Override
  public URI getHref() {
    return (id != null) ? entityLinks.linkForItemResource(Calendar.class, id).toUri() : null;
  }

  /**
   * The PostConstruct annotation is used on the method that needs to be executed after dependency injection
   */
  @PostConstruct
  private void initHateoas() {
    //noinspection ResultOfMethodCallIgnored
    entityLinks.toString();
  }

  public enum MealSlot {
    BREAKFAST,
    LUNCH,
    DINNER
  }

}