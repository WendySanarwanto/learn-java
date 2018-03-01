package me.wendysa.contactsdemo.services;

import java.text.*;
import java.util.Date;

import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.models.*;
import me.wendysa.contactsdemo.repositories.ScheduleRepository;

public class ScheduleService implements ISchedulable {
  private ScheduleRepository repository;
  public final static String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm";

  public ScheduleService(ScheduleRepository repository) {
    this.repository = repository;
  }

  @Override
  public Schedule doSchedule(String beginDate, String endDate, Contact[] participants, String description,
      String organiser, String title) {
    // NOTE- Normally we should perform validation against the input parameters
    SimpleDateFormat sdf = new SimpleDateFormat(ScheduleService.DATE_TIME_FORMAT);
    Date dateBegin = sdf.parse(beginDate, new ParsePosition(0));
    Date dateEnd = sdf.parse(endDate, new ParsePosition(0));
    
    // System.out.println(String.format("[DEBUG] - <ScheduleService.doSchedule> dateBegin: %s", dateBegin));
    // System.out.println(String.format("[DEBUG] - <ScheduleService.doSchedule> dateEnd: %s", dateEnd));

    Schedule newSchedule = new Schedule(dateBegin, dateEnd, participants, description, organiser, title);

    return this.repository.push(newSchedule) ? newSchedule : null;
  }
}