package org.benford.zscore;

public class ZScoreCalculator {

  private final double continuityCorrectionFactor;
  private final double[] digitDistributioin;
  private final int count;

  public ZScoreCalculator(int count, double[] digitDistributioin) {
    this.count = count;
    this.continuityCorrectionFactor = 1/(2 * (double) count);
    this.digitDistributioin = digitDistributioin;
  }

  public double[] calculateZscore(double[] expected) {
    double[] zscore = new double[10];

    for (int i = 0; i < digitDistributioin.length; i++) {
      zscore[i] = calculateZscore(digitDistributioin[i], expected[i]);
    }

    return zscore;
  }

  public Double calculateZscore(Double actual, Double expected) {
    double abs = Math.abs(actual - expected);
    double ccf = continuityCorrectionFactor < abs ? continuityCorrectionFactor : 0;
    Double numerator = abs - ccf;
    Double denominator = Math.sqrt((expected * (1 - expected)) / count);
    return numerator / denominator;
  }
}