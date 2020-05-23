package org.benford;

import org.benford.score.ChiSquare;
import org.benford.score.ChiSquareCalculator;
import org.benford.score.ZScore;
import org.benford.score.ZScoreCalculator;

import static org.benford.BenfordConst.FIRST_DIGIT_DISTRIBUTION;
import static org.benford.BenfordConst.SECOND_DIGIT_DISTRIBUTION;

public class BenfordSeries extends Series {

  private double[] digitDistribution;
  private int count;
  private ZScoreCalculator zScoreCalculator;
  private ChiSquareCalculator chiSquareCalculator;
  private ZScore zscoreFirstDigit;
  private ZScore zscoreSecondDigit;
  private ChiSquare chiSquareFirstDigit;

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
      double[] series = getZScoreCalculator().calculateZscore(FIRST_DIGIT_DISTRIBUTION);
      zscoreFirstDigit = new ZScore(series);
    }

    return zscoreFirstDigit;
  }

  public ZScore getZscoreSecondDigit() {
    if (zscoreSecondDigit == null) {
      double[] series = getZScoreCalculator().calculateZscore(SECOND_DIGIT_DISTRIBUTION);
      zscoreSecondDigit = new ZScore(series);
    }

    return zscoreSecondDigit;
  }

  public ChiSquare getChiSquareFirstDigit() {
    if (chiSquareFirstDigit == null) {
      double[] series = getChiSquareCalculator().calculateChiSquare(FIRST_DIGIT_DISTRIBUTION);
      chiSquareFirstDigit = new ChiSquare(series);
    }

    return chiSquareFirstDigit;
  }

  private ZScoreCalculator getZScoreCalculator() {
    if (zScoreCalculator == null) {
      zScoreCalculator = new ZScoreCalculator(getCount(), getDigitDistribution());
    }
    return zScoreCalculator;
  }

  private ChiSquareCalculator getChiSquareCalculator() {
    if (chiSquareCalculator == null) {
      chiSquareCalculator = new ChiSquareCalculator(getCount(), getDigitDistribution());
    }
    return chiSquareCalculator;
  }

  private double[] calcDigitDistribution() {
    double[] distribution = new double[10];

    for (int i = 0; i < series.length; i++) {
      distribution[i] = series[i] / getCount();
    }

    return distribution;
  }
}
