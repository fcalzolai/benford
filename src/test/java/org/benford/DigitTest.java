package org.benford;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DigitTest {

  @Test
  public void firstDigit(){
    DistributionTest(new FirstDigitDistribution().getSeries());
  }

  @Test
  public void secondDigitDistribution(){
    DistributionTest(new SecondDigitDistribution().getSeries());
  }

  @Test
  public void lessThan1() {
    double[] series = getArray(0.0);
    assertThrows(IllegalArgumentException.class, () -> new DigitDistribution(series));
  }

  @Test
  public void greaterThan1() {
    double[] series = getArray(0.2);
    assertThrows(IllegalArgumentException.class, () -> new DigitDistribution(series));
  }

  private double[] getArray(Double d) {
    return new double[]{
            d,
            d,
            d,
            d,
            d,
            d,
            d,
            d,
            d,
            d,
    };
  }

  private void DistributionTest(double[] series) {
    Double actual = 0.0;
    for (Double d : series) {
      actual += d;
    }

    assertEquals(1.0, actual, Consts.DELTA);
  }

}