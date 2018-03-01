package me.wendysa.contactsdemo.contracts;

import me.wendysa.contactsdemo.models.*;

public interface ISchedulable {
  public Schedule doSchedule(String beginDate, String endDate, Contact[] participants, String description,
      String organiser, String title);
}