package edu.cnm.deepdive.mealornomeal.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Meal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "meal_id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "instruction", length = 5000, nullable = false)
  private String instruction;

  @Column(name = "prep_time")
  private Integer prepTime;

  @Column(name = "requirements", length = 200)
  private String requirements;

  @ManyToOne

}
