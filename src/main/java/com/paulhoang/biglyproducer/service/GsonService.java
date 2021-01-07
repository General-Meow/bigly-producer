package com.paulhoang.biglyproducer.service;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GsonService {

  private final Gson gson;

  @Autowired
  public GsonService(Gson gson) {
    this.gson = gson;
  }

  public String toJson(Object object) {
    Preconditions.checkArgument(object != null, "ojbect cannot be null");
    return gson.toJson(object);
  }
}
