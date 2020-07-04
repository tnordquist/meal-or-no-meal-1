package edu.cnm.deepdive.mealornomeal.model.service;

import android.nfc.Tag;
import edu.cnm.deepdive.mealornomeal.model.entity.Calendar;
import javax.xml.transform.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

  @Query
  Iterable<Calendar> getAllByOrderByTextAsc();

  @Query
  Iterable<Calendar> getAllBySourceOrderByTextAsc(Source source);

  @Query
  Iterable<Calendar> getAllByTagsContainingOrderByTextAsc(Tag tag);

}
