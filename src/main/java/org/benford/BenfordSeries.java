package org.benford;

public class BenfordSeries extends Series {

  private double[] digitDistribution;
  private int count;
  private ZScore zscoreFirstDigit;
  private ZScore zscoreSecondDigit;

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

  public ZScore getZscoreFirstDigit() {
    if (zscoreFirstDigit == null) {
      double[] series = calculateZscore(BenfordConst.FIRST_DIGIT_DISTRIBUTION);
      zscoreFirstDigit = new ZScore(series);
    }

    return zscoreFirstDigit;
  }

  public ZScore getZscoreSecondDigit() {
    if (zscoreSecondDigit == null) {
      double[] series = calculateZscore(BenfordConst.SECOND_DIGIT_DISTRIBUTION);
      zscoreSecondDigit = new ZScore(series);
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
