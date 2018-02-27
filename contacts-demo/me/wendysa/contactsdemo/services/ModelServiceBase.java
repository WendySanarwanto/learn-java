package me.wendysa.contactsdemo.services;

import me.wendysa.contactsdemo.exceptions.*;

public abstract class ModelServiceBase<TModel> {
  
  public void keepNewModelInstance(TModel newModel, TModel[] modelsList) throws NoAvailableSlotException {
    // find available slot in contact list:
    for(int i=0; i< modelsList.length; i++){
      if (modelsList[i] == null) {
        modelsList[i] = newModel;
        return;
      }
    }
    throw new NoAvailableSlotException();
  }
}