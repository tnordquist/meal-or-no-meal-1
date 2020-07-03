package edu.cnm.deepdive.mealornomeal.model.entity;

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

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(
    indexes = {
        @Index(columnList = "name")
    }
)
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
  private String required;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "creator_id")
  private User creator;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public Integer getPrepTime() {
    return prepTime;
  }

  public void setPrepTime(Integer prepTime) {
    this.prepTime = prepTime;
  }

  public String getRequirements() {
    return required;
  }

  public void setRequirements(String requirements) {
    this.required = requirements;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }
}
