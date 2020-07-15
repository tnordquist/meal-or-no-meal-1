package edu.cnm.deepdive.mealornomeal.model.entity;

import edu.cnm.deepdive.mealornomeal.controller.view.FlatCalendar;
import java.net.URI;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(
    indexes = {
        @Index(columnList = "name")
    }
)
public class Calendar implements FlatCalendar {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "calendar_id", nullable = false, updatable = false)
  private Long id;


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "meal_id", nullable = false, updatable = false)
  private Meal meal;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User creator;

  @Column(nullable = false)
  private LocalDate date;

  @Column(nullable = false)
  private int mealSlot;

  public Long getId() {
    return id;
  }

  @Override
  public String getName() {
    return null;
  }

  public Meal getMeal() {
    return meal;
  }

  public void setMeal(Meal meal) {
    this.meal = meal;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getMealSlot() {
    return mealSlot;
  }

  public void setMealSlot(int mealSlot) {
    this.mealSlot = mealSlot;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Autowired
  public static void setEntityLinks(EntityLinks entityLinks) {
    Calendar.entityLinks = entityLinks;
  }

  @Override
  public URI getHref() {
    return (id != null) ? entityLinks.linkForItemResource(Calendar.class, id).toUri() : null;
  }

  @PostConstruct
  private void initHateoas() {
    //noinspection ResultOfMethodCallIgnored
    entityLinks.toString();
  }

}