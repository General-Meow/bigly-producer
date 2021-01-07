package com.paulhoang.biglyproducer.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GsonServiceTest {

  @Mock
  private Gson gsonMock;

  @InjectMocks
  private GsonService testObj;

  @Test
  void toJsonShouldThrowExceptionWhenParameterIsNull() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      testObj.toJson(null);
    });

    verifyNoInteractions(gsonMock);
  }

  @Test
  void toJsonShouldReturnAJsonVersionOfTheParameter() {
    Object parameter = new Object();
    String expected = "json version of param";
    when(gsonMock.toJson(parameter)).thenReturn(expected);

    String result = testObj.toJson(parameter);

    Assertions.assertEquals(expected, result);
    verify(gsonMock).toJson(parameter);
  }
}