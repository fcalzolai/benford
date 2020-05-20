package org.benford2;

public class BenfordCalculator {

  private final double[] series;

  private double[] digitDistribution;
  private int count;
  private double[] zscoreFirstDigit;
  private double[] zscoreSecondDigit;

  public BenfordCalculator(double[] series) {
    this.series = series;
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

  public double[] getZscoreFirstDigit() {
    if (zscoreFirstDigit == null) {
      zscoreFirstDigit = calculateZscore(BenfordConst.FIRST_DIGIT_DISTRIBUTION);
    }

    return zscoreFirstDigit;
  }

  public double[] getZscoreSecondDigit() {
    if (zscoreSecondDigit == null) {
      zscoreSecondDigit = calculateZscore(BenfordConst.SECOND_DIGIT_DISTRIBUTION);
    }

    return zscoreSecondDigit;
  }

  private double[] calculateZscore(double[] expected_distribution) {
    double[] zscore = new double[10];

    double[] actual = getDigitDistribution();
    for (int i = 0; i < actual.length; i++) {
      zscore[i] = calculateZscore(actual[i], expected_distribution[i]);
    }

    return zscore;
  }

  private Double calculateZscore(Double actual, Double expected) {
    Double numerator = Math.abs(actual - expected);
    Double denominator = Math.sqrt((expected * (1 - expected)) / count);
    return numerator / denominator;
  }

  private double[] calcDigitDistribution() {
    double[] distribution = new double[10];

    for (int i = 0; i < series.length; i++) {
      distribution[i] = series[i] / getCount();
    }

    return distribution;
  }
}
