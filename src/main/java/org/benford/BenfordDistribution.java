package org.benford;

public class BenfordDistribution extends DigitDistribution {

  private final int count;
  private DigitSeries zscore;

  public BenfordDistribution(int count, Double[] series) {
    super(series);
    this.count = count;
  }

  public DigitSeries getZscore(DigitSeries expected) {
    if (zscore == null) {
      for (int i = 0; i < series.length; i++) {
        series[i] = getZscore(series[i], expected.series[i]);
      }
      zscore = new DigitSeries(series);
    }

    return zscore;
  }

  private Double getZscore(Double actual, Double expected) {
    double numerator = Math.abs(actual - expected);
    double denominator = Math.sqrt((expected * (1 - expected)) / count);
    return numerator / denominator;
  }
}

