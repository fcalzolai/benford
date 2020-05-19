package org.benford;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DoubleSeries {

  double[] series;

  public DoubleSeries(double[] series) {
    checkSeriesSize(series);
    this.series = series;
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
