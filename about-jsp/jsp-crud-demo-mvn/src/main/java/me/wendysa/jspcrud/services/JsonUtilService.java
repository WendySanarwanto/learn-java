package me.wendysa.jspcrud.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtilService {
  public static <T> String jsonStringify( T target ) 
    throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(target);
  }

  public static <T> T doParse(String stringifiedJson, Class<T> resultType ) 
    throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    return (T) mapper.readValue(stringifiedJson, resultType);
  }
}
