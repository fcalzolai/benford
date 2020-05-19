package org.benford;

import static org.benford.Consts.SUM_SERIE;

public class DigitDistribution extends DigitSeries {

  public DigitDistribution(double[] series) {
    super(series);
    checkDistribution(series);
  }

  private void checkDistribution(double[] series) {
    Double actual = 0.0;
    for (Double d : series) {
      actual += d;
    }

    if (Math.abs(actual - SUM_SERIE) > Consts.DELTA) {
      throw new IllegalArgumentException(String.format("Invalid series value: {%s}. It should be {%s}", actual, SUM_SERIE));
    }
  }
}

