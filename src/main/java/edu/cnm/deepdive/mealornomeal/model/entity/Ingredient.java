package edu.cnm.deepdive.mealornomeal.model.entity;

import edu.cnm.deepdive.mealornomeal.controller.view.FlatIngredient;
import java.net.URI;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.lang.NonNull;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Ingredient implements FlatIngredient {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ingredient_id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "quantity", length = 50, nullable = false)
  private String quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meal_id")
  private Meal meal_id;

  public void setName(String name) {
    this.name = name;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  @Override
  public URI getHref() {
    return null;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public String getQuantity() {
    return quantity;
  }

}
