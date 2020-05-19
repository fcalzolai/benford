package org.benford;

public class BenfordDistribution extends DigitDistribution {

  private final int count;
  private DoubleSeries zscore;

  public BenfordDistribution(int count, double[] series) {
    super(series);
    this.count = count;
  }

  public DoubleSeries getZscore(DoubleSeries expected) {
    if (zscore == null) {
      for (int i = 0; i < series.length; i++) {
        series[i] = getZscore(series[i], expected.series[i]);
      }
      zscore = new DoubleSeries(series);
    }

    return zscore;
  }

  private Double getZscore(Double actual, Double expected) {
    double numerator = Math.abs(actual - expected);
    double denominator = Math.sqrt((expected * (1 - expected)) / count);
    return numerator / denominator;
  }
}

