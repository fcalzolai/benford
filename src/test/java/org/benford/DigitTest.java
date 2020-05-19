package org.benford;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitTest {

  @Test
  public void firstDigit(){
    DistributionTest(new FirstDigitDistribution().getSeries());
  }

  @Test
  public void secondDigitDistribution(){
    DistributionTest(new SecondDigitDistribution().getSeries());
  }

  private void DistributionTest(double[] serie) {
    Double actual = 0.0;
    for (Double d : serie) {
      actual += d;
    }

    assertEquals(1.0, actual, Consts.DELTA);
  }

}