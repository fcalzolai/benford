package org.benford;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PercentageTest {

  @Test
  public void lessThan1() {
    Double[] serie = getArray(0.0);
    assertThrows(IllegalArgumentException.class, () -> new DigitDistribution(serie));
  }

  @Test
  public void greaterThan1() {
    Double[] serie = getArray(0.2);
    assertThrows(IllegalArgumentException.class, () -> new DigitDistribution(serie));
  }

  private Double[] getArray(Double d) {
    return new Double[]{
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
