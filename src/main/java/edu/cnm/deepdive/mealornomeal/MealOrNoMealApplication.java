package edu.cnm.deepdive.mealornomeal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * This class provides a framework for managing a JavaFx.
 */
@SpringBootApplication
@EnableWebSecurity
@EnableResourceServer
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class MealOrNoMealApplication extends ResourceServerConfigurerAdapter {

  @Value("${oauth.clientId}")
  private String clientId;

  /**
   * The class invokes this method in order to run.
   * @param args
   */

  public static void main(String[] args) {
    SpringApplication.run(MealOrNoMealApplication.class, args);
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(clientId);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
//        .antMatchers(HttpMethod.POST, "/meals").hasRole("USER")
//        .antMatchers(HttpMethod.PUT, "/meals/**").hasRole("USER")
//        .antMatchers(HttpMethod.DELETE, "/meals/**").hasRole("USER")
//        .antMatchers(HttpMethod.POST, "/calendar").hasRole("USER")
//        .antMatchers(HttpMethod.PUT, "/calendar/**").hasRole("USER")
//        .antMatchers(HttpMethod.DELETE, "/calendar/**").hasRole("USER")
//        .antMatchers(HttpMethod.GET, "/calendar/**").hasRole("USER")
        .anyRequest().permitAll();
  }
}
