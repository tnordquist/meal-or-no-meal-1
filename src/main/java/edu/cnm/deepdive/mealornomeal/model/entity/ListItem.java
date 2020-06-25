package edu.cnm.deepdive.mealornomeal.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ListItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "list_item_id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "quantity", length = 50, nullable = false)
  private String quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private Long user_id;

}

}
