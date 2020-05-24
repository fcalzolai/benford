package org.benford.score;

public class ZScoreCalculator {

  private static final double EPS = 0.001;

  private final double continuityCorrectionFactor;
  private final double[] digitDistribution;
  private final int count;

  public ZScoreCalculator(int count, double[] digitDistribution) {
    checkDigitDistribution(digitDistribution);
    this.count = count;
    this.continuityCorrectionFactor = 1/(2 * (double) count);
    this.digitDistribution = digitDistribution;
  }

  public double[] calculateZscore(double[] expected) {
    double[] zscore = new double[10];

    for (int i = 0; i < digitDistribution.length; i++) {
      zscore[i] = calculateZscore(digitDistribution[i], expected[i]);
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

  private void checkDigitDistribution(double[] digitDistribution) {
    if (digitDistribution.length != 10) {
      throw new IllegalArgumentException("digit distribution length must be 10");
    }

    double tot = 0;
    for (double d : digitDistribution) {
      if (d > 1.0) {
        throw new IllegalArgumentException("All the digit must be less then 1.0");
      }
      tot += d;
    }

    if (Math.abs(tot - 1.0) > EPS) {
      throw new IllegalArgumentException("The sum of all the distribution must be 1.0");
    }
  }
}