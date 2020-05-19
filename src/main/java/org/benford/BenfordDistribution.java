package org.benford;

import javax.validation.constraints.Size;

public class BenfordDistribution extends DigitSerie {

  private int count;

  public BenfordDistribution(int count) {
    super();
    this.count = count;
  }

  public BenfordDistribution(@Size(min = 10, max = 10) Double[] serie, int count) {
    super(serie);
    this.count = count;
  }

  public DigitSerie getZscore(DigitSerie expected) {
    Double[] zscore = new Double[10];
    for (int i = 0; i < serie.length; i++) {
      zscore[i] = getZscore(serie[i], expected.serie[i]);
    }

    return new DigitSerie(zscore);
  }

  private Double getZscore(Double actual, Double expected) {
    double numerator = Math.abs(actual - expected);
    double denominator = Math.sqrt((expected * (1 - expected)) / count);
    return numerator / denominator;
  }
}
