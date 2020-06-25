package edu.cnm.deepdive.mealornomeal.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



  @SuppressWarnings("JpaDataSourceORMInspection")
  @Entity
  public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
