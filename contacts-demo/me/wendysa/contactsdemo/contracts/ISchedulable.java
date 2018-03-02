package me.wendysa.contactsdemo.contracts;

import java.util.*;
import me.wendysa.contactsdemo.models.*;

public interface ISchedulable {
  public enum SortBy { START_DATE_DESC, START_DATE_ASC };
  public Schedule doSchedule(String beginDate, String endDate, Contact[] participants, String description,
      String organiser, String title);

  public List<Schedule> getSchedules(Optional<SortBy> sortBy_);
}