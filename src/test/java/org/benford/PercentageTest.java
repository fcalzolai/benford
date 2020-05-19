package org.benford;

import org.junit.jupiter.api.Test;

public class PercentageTest {

  @Test
  public void wrongDistribution() {
    Double[] serie = getArray(0.0);
    DigitDistribution digitDistribution = new DigitDistribution(serie);

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
