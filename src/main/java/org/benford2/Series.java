package org.benford2;

import lombok.ToString;

@ToString
public class Series {

  protected final double[] series;

  public Series(double[] series) {
    this.series = series;
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
