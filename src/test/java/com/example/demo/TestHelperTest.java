package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHelperTest {

  @Test
  public void name() throws Exception {
    TestHelper testHelper = new TestHelper();
    int result = testHelper.add(1,2);
    assertEquals(3, result);
  }
}