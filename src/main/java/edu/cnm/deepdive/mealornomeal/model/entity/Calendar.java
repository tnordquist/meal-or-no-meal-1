package edu.cnm.deepdive.mealornomeal.model.entity;

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
  @JoinColumn(name = "meal_id")
  private Calendar calendar;


  @Column(nullable = false, length = 20)
  private Long calendarDate;

  public void setId(Long id) {
    this.id = id;
  }

  @Column(nullable = false, length = 10)
  private Long mealSlot;

  public Calendar getCalendar() {
    return calendar;
  }

  public void setCalendar(Calendar calendar) {
    this.calendar = calendar;
  }

  public Long getCalendarDate() {
    return calendarDate;
  }

  public void setCalendarDate(Long calendarDate) {
    this.calendarDate = calendarDate;
  }

  public Long getMealSlot() {
    return mealSlot;
  }

  public void setMealSlot(Long mealSlot) {
    this.mealSlot = mealSlot;
  }
}