package me.wendysa.contactsdemo.repositories;

import java.util.*;

import me.wendysa.contactsdemo.contracts.IRepository;
import me.wendysa.contactsdemo.models.Schedule;;

public class ScheduleRepository implements IRepository<Schedule> {
  private final ArrayList<Schedule> storage = new ArrayList<Schedule>();

  @Override
  public Schedule push(Schedule newSchedule) {
    return storage.add(newSchedule) ? newSchedule : null;
  }

  @Override
  public List<Schedule> getAll() {
    return Collections.synchronizedList(storage);
  }

  @Override
  public void deleteAll() {
    this.storage.clear();
  }
}