package org.benford;

import lombok.Getter;

@Getter
public class DigitSeries {

  double[] series;

  public DigitSeries(double[] series) {
    checkSeriesSize(series);
    this.series = series;
  }

  public DigitSeries() {
    series = new double[10];
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  private void checkSeriesSize(double[] series) {
    if (series == null) {
      throw new IllegalArgumentException("Series cannot be null");
    }

    if (series.length != 10) {
      throw new IllegalArgumentException(String.format("Invalid series's length:{%s}. Length must be 10", series.length));
    }
  }
}
