package org.benford;

public class BenfordSeries extends DoubleSeries {

  private int count;

  public BenfordSeries(double[] serie) {
    super(serie);
  }

  public int getCount() {
    if (count == 0) {
      for (double d: series) {
        count += d;
      }
    }

    return count;
  }

  public DoubleSeries getZscore(DigitDistribution digitDistribution) {
    return getDistribution().getZscore(digitDistribution);
  }

  private BenfordDistribution getDistribution() {
    double[] series2 = new double[10];
    for (int i = 0; i < series.length; i++) {
      series2[i] = series[i] / getCount();
    }

    return new BenfordDistribution(getCount(), series2);
  }
}
