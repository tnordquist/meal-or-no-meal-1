package edu.cnm.deepdive.mealornomeal.model.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class
Calendar {

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
}