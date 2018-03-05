package me.wendysa.contactsdemo.repositories;

import java.util.*;

import me.wendysa.contactsdemo.contracts.IRepository;
import me.wendysa.contactsdemo.models.Schedule;;

public class ScheduleRepository implements IRepository<Schedule> {
  private final ArrayList<Schedule> storage = new ArrayList<Schedule>();

  public Schedule push(Schedule newSchedule) {
    return storage.add(newSchedule) ? newSchedule : null;
  }

  public List<Schedule> getAll() {
    return Collections.synchronizedList(storage);
  }
}