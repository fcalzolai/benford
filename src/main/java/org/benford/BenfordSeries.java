package org.benford;

import org.benford.score.ScoreHandler;
import org.benford.score.ZScoreCalculator;
import org.benford.score.ZScoreResult;

import static org.benford.BenfordConst.FIRST_DIGIT_DISTRIBUTION;

public class BenfordSeries extends Series {

  private double[] digitDistribution;
  private int count;
  private ZScoreCalculator zScoreCalculator;
  private ZScoreResult zscoreFirstDigit;

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

  public ZScoreResult getZscoreFirstDigit() {
    if (zscoreFirstDigit == null) {
      ScoreHandler result = getZScoreCalculator().getScoreHandler(FIRST_DIGIT_DISTRIBUTION);
      zscoreFirstDigit = new ZScoreResult(result.getSeries());
    }

    return zscoreFirstDigit;
  }

  private ZScoreCalculator getZScoreCalculator() {
    if (zScoreCalculator == null) {
      zScoreCalculator = new ZScoreCalculator(getCount(), getDigitDistribution());
    }
    return zScoreCalculator;
  }

  private double[] calcDigitDistribution() {
    double[] distribution = new double[10];

    for (int i = 0; i < series.length; i++) {
      distribution[i] = series[i] / getCount();
    }

    return distribution;
  }
}
