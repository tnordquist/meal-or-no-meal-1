package edu.cnm.deepdive.mealornomeal.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.cnm.deepdive.mealornomeal.controller.view.FlatUser;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * The user interface holds the information of the user.
 */

  @SuppressWarnings("JpaDataSourceORMInspection")
  @Entity
  public class User implements FlatUser {

  /**
   * Implementing this interface allows
   */

  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @Column(nullable = false, updatable = false, unique = true)
    private String oauthKey;


    public Long getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getOauthKey() {
      return oauthKey;
    }

    public void setOauthKey(String oauthKey) {
      this.oauthKey = oauthKey;
    }

    public enum Role {
      USER, CREATOR
    }

  }
