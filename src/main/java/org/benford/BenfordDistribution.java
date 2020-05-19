package org.benford;

import javax.validation.constraints.Size;

public class BenfordDistribution extends DigitSeries {

  private int count;

  public BenfordDistribution(int count) {
    super();
    this.count = count;
  }

  public BenfordDistribution(@Size(min = 10, max = 10) Double[] serie, int count) {
    super(serie);
    this.count = count;
  }

  public DigitSeries getZscore(DigitSeries expected) {
    Double[] zscore = new Double[10];
    for (int i = 0; i < series.length; i++) {
      zscore[i] = getZscore(series[i], expected.series[i]);
    }

    return new DigitSeries(zscore);
  }

  private Double getZscore(Double actual, Double expected) {
    double numerator = Math.abs(actual - expected);
    double denominator = Math.sqrt((expected * (1 - expected)) / count);
    return numerator / denominator;
  }
}
