package org.benford;

public class BenfordSeries extends Series {

  private double[] digitDistribution;
  private int count;

  public BenfordSeries(double[] series) {
    super(series);
  }

  public double[] getDigitDistribution() {
    if (digitDistribution == null) {
      digitDistribution = calcDigitDistribution();
    }

    return digitDistribution;
  }

  public int getCount() {
    if (count == 0) {
      for (double d : series) {
        count += d;
      }
    }

    return count;
  }

  private double[] calcDigitDistribution() {
    double[] distribution = new double[10];

    for (int i = 0; i < series.length; i++) {
      distribution[i] = series[i] / getCount();
    }

    return distribution;
  }
}
