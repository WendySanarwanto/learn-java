package me.wendysa.contactsdemo.repositories;

import java.util.*;
import me.wendysa.contactsdemo.models.*;

public class ScheduleRepository {
  private final ArrayList<Schedule> storage = new ArrayList<Schedule>();

  public boolean push(Schedule newSchedule) {
    return storage.add(newSchedule);
  }
}