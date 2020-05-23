package org.benford.printer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CountryCalculatorTest {

  @Test
  void getCountry() {
    String expected = CountryCalculator.getCountry("WID_data_IT.csv");
    Assertions.assertEquals("Italy", expected);
  }
}