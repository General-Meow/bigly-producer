package com.paulhoang.biglyproducer.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GsonService {

  private Gson gson;

  @Autowired
  public GsonService(Gson gson) {
    this.gson = gson;
  }

  public String toJson(Object object) {
    return gson.toJson(object);
  }
}
