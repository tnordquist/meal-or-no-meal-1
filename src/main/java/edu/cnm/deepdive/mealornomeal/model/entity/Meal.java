package edu.cnm.deepdive.mealornomeal.model.entity;

import edu.cnm.deepdive.mealornomeal.controller.view.FlatMeal;
import java.net.URI;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
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

/**
 * This is th Entity model for Meal representing
 */

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Component
@Table(
    indexes = {
        @Index(columnList = "name")
    }
)
public class Meal implements FlatMeal {

  private static EntityLinks entityLinks;

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

  @ManyToOne(fetch = FetchType.EAGER,
  cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "creator_id")
  private User creator;

  /**
   * Gets Id
   *
   */

  public Long getId() {
    return id;
  }

  /**
   * Gets Name
   *
   */

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets Instructions
   *
   */

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  /**
   * Gets PreTime
   *
   */

  public Integer getPrepTime() {
    return prepTime;
  }

  public void setPrepTime(Integer prepTime) {
    this.prepTime = prepTime;
  }

  /**
   * Gets Requirements
   *
   */

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

  @PostConstruct
  private void initHateoas() {
    //noinspection ResultOfMethodCallIgnored
    entityLinks.toString();
  }

  @Autowired
  private void setEntityLinks(
      @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") EntityLinks entityLinks) {
    Meal.entityLinks = entityLinks;
  }

  @Override
  public URI getHref() {
    return (id != null) ? entityLinks.linkForItemResource(Meal.class, id).toUri() : null;
  }

}
