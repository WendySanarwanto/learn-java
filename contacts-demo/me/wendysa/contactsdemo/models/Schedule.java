package me.wendysa.contactsdemo.models;

import java.text.*;
import java.util.*;

public class Schedule {
  private Date beginDate;
  private Date endDate;
  private Contact[] participants;
  private String description;
  private String organiser;
  private String title;

  public Schedule(Date beginDate, Date endDate, Contact[] participants, String description, String organiser,
      String title) {
    this.title = title;
    this.beginDate = beginDate;
    this.endDate = endDate;
    this.participants = participants;
    this.description = description;
    this.organiser = organiser;
    this.title = title;
  }

  public Date getBeginDate() {
    return this.beginDate;
  }

  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }

  public Date getEndDate() {
    return this.beginDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Contact[] getParticipants() {
    return this.participants;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOrganiser() {
    return this.organiser;
  }

  public void setOrganiser(String organiser) {
    this.organiser = organiser;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\n------------------\n");
    sb.append(title);
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, @ hh:mm a");
    // System.out.println(String.format("beginDate: %s", sdf.format(beginDate)));
    sb.append(String.format("\nStart        : %s", sdf.format(beginDate)));
    sb.append(String.format("\nEnd          : %s", sdf.format(endDate)));
    StringBuilder sbParticipants = new StringBuilder();
    for(int i=0; i < this.participants.length; i++){
      sbParticipants.append(String.format("%s ", this.participants[i].getName()));
    }
    sb.append(String.format("\nParticipants : %s\n", sbParticipants.toString()));
    sb.append(String.format("\nOrganiser    : %s\n", organiser));
    sb.append(String.format("Description  : \n%s", description));
    return sb.toString();
  }
}