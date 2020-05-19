package org.benford;

import static org.benford.Consts.SUM_SERIE;

public class DigitDistribution extends DoubleSeries {

  public DigitDistribution(double[] series) {
    super(series);
    checkDistribution(series);
  }

  private void checkDistribution(double[] series) {
    double actual = 0.0;
    for (double d : series) {
      actual += d;
    }

    if (Math.abs(actual - SUM_SERIE) > Consts.DELTA) {
      throw new IllegalArgumentException(String.format("Invalid series value: {%s}. It should be {%s}", actual, SUM_SERIE));
    }
  }
}

