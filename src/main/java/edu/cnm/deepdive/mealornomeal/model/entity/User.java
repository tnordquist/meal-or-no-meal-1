package edu.cnm.deepdive.mealornomeal.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.cnm.deepdive.mealornomeal.view.FlatUser;
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
   * Implementing this interface allows the User creat an user Id.
   */

  @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;

  /**
   * This allow the User to creat a unique user name.
   */

    @Column(name = "name", nullable = false, unique = true)
    private String name;

  @JsonIgnore
    @Column(nullable = false, updatable = false, unique = true)
    private String oauthKey;

  /**
   * gets the user id
   * @return
   */
  public Long getId() {
      return id;
    }

  /**
   * gets the name of the user
   * @return
   */
  public String getName() {
      return name;
    }

  /**
   * Sets the user name
   * @param name
   */

  public void setName(String name) {
      this.name = name;
    }

  /**
   * gets the oauthKey from google
   * @return
   */

  public String getOauthKey() {
      return oauthKey;
    }

  /**
   *  Sets the oauthKey for the user.
   * @param oauthKey
   */

  public void setOauthKey(String oauthKey) {
      this.oauthKey = oauthKey;
    }

    public enum Role {
      USER, CREATOR
    }

  }
