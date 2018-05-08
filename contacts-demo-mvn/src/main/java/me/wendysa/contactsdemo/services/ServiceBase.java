package me.wendysa.contactsdemo.services;

import java.util.List;

import me.wendysa.contactsdemo.contracts.IRepository;

public abstract class ServiceBase<TModel> {
  private final IRepository<TModel> repository;

  public ServiceBase(IRepository<TModel> repository){
    this.repository = repository;
  }

  protected List<TModel> getAll(){
    return repository.getAll();
  }

  protected TModel push(TModel data) {
    return repository.push(data);
  }

  protected void removeAll() {
    repository.deleteAll();
  }
}