package org.benford;

import lombok.Getter;

@Getter
public class DigitSeries {

  Double[] series;

  public DigitSeries(Double[] series) {
    checkSeriesSize(series);
    this.series = series;
  }

  public DigitSeries() {
    series = new Double[10];
  }

  private void checkSeriesSize(Double[] series) {
    if (series == null) {
      throw new IllegalArgumentException("Series cannot be null");
    }

    if (series.length != 10) {
      throw new IllegalArgumentException(String.format("Invalid series's length:{%s}. Length must be 10", series.length));
    }
  }
}
