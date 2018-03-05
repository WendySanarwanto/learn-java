package me.wendysa.contactsdemo.contracts;

import java.util.*;

public interface IRepository<TModel> {
  List<TModel> getAll();
  TModel push(TModel newSchedule);
}
