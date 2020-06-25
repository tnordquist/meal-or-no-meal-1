package edu.cnm.deepdive.mealornomeal.model.entity;

import android.os.Parcelable.Creator;
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
public class Calendar {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "calendar_id", nullable = false, updatable = false)
  private Long id;


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "meal_id", nullable = false, updatable = false)
  private Meal meal;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private Calendar creator;

  @Column(nullable = false, length = 25)
  private Long calendarDate;


  @Column(nullable = false, length = 20)
  private Long mealSlot;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Meal getMeal() {
    return meal;
  }

  public Calendar getCreator() {
    return creator;
  }

  public Long getCalendarDate() {
    return calendarDate;
  }

  public Long getMealSlot() {
    return mealSlot;
  }

}