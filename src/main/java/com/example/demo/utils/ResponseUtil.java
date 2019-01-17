package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

  public static ResponseEntity success(Object o) {
    Result result = new Result<>(o);
    result.setCode("SUCCESS");
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
