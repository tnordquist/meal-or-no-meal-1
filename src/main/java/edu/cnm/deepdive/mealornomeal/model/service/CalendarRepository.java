package edu.cnm.deepdive.mealornomeal.model.service;

//import android.nfc.Tag; -- commented this out to remove error. Not sure it's needed but thought
//I'd retain in-case it was being invoked for a purpose.  - Levi
import edu.cnm.deepdive.mealornomeal.model.entity.Calendar;
import javax.xml.transform.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This Repository gathers all requested data, organizes adn displays it per the instructions given.
 */
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

  /**
   * This @Query organizes the searched calendars by date after getting them by their name.
   * @param id
   * @return
   */
//  @Query
//  Iterable<Calendar> getAllByNameOrderByDateAsc(String id);

  /**
   * This @Query organizes the searched calendars by date after searching for them by dates.
   * @param id
   * @return
   */
  @Query
  Iterable<Calendar> getAllByDate(long id);

}
