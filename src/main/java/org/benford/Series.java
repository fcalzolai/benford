package org.benford;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Series {

  protected final double[] series;

  public Series(double[] series) {
    this.series = series;
    checkSeriesSize(series);
  }

  protected void checkSeriesSize(double[] series) {
    if (series == null) {
      throw new IllegalArgumentException("Series cannot be null");
    }

    if (series.length != 10) {
      throw new IllegalArgumentException(String.format("Invalid series's length:{%s}. Length must be 10", series.length));
    }
  }
}
