package org.benford;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PercentageTest {

  @Test
  public void lessThan1() {
    double[] serie = getArray(0.0);
    assertThrows(IllegalArgumentException.class, () -> new DigitDistribution(serie));
  }

  @Test
  public void greaterThan1() {
    double[] serie = getArray(0.2);
    assertThrows(IllegalArgumentException.class, () -> new DigitDistribution(serie));
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
}
