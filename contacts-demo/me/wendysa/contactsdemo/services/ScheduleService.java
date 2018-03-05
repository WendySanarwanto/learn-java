package me.wendysa.contactsdemo.services;

import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import me.wendysa.contactsdemo.contracts.*;
import me.wendysa.contactsdemo.models.*;
import me.wendysa.contactsdemo.repositories.ScheduleRepository;

public class ScheduleService extends ServiceBase<Schedule> implements ISchedulable {
  public final static String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm";

  public ScheduleService(ScheduleRepository repository) {
    super(repository);
  }

  @Override
  public Schedule doSchedule(String beginDate, String endDate, List<Contact> participants, String description,
      String organiser, String title) {
    // NOTE- Normally we should perform validation against the input parameters
    SimpleDateFormat sdf = new SimpleDateFormat(ScheduleService.DATE_TIME_FORMAT);
    Date dateBegin = sdf.parse(beginDate, new ParsePosition(0));
    Date dateEnd = sdf.parse(endDate, new ParsePosition(0));
    
    // System.out.println(String.format("[DEBUG] - <ScheduleService.doSchedule> dateBegin: %s", dateBegin));
    // System.out.println(String.format("[DEBUG] - <ScheduleService.doSchedule> dateEnd: %s", dateEnd));

    Schedule newSchedule = new Schedule(dateBegin, dateEnd, participants, description, organiser, title);
    return this.push(newSchedule);
  }

  @Override
  public List<Schedule> getSchedules(Optional<SortBy> sortBy_) {
    SortBy sortBy = sortBy_ != null ? sortBy_.get() : SortBy.START_DATE_ASC;
    List<Schedule> schedules = this.getAll();

    if (sortBy == SortBy.START_DATE_ASC) {
      schedules.sort((left, right) -> (int) left.getBeginDate().getTime() - (int)right.getBeginDate().getTime() );
    } else {
      schedules.sort((left, right) -> (int) right.getBeginDate().getTime() - (int)left.getBeginDate().getTime() );
    }

    return schedules;
  }

  @Override
  public List<Schedule> getSchedulesByParticipant(Optional<Contact> participant_) {
    Contact participant = participant_ != null ? participant_.get() : null;
    List<Schedule> schedules = this.getAll();

    return schedules.stream()
            .filter(schedule->schedule.getParticipants().indexOf(participant) > -1)
            .collect(Collectors.toList());
  }  
}