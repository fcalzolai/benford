package org.benford.score;

import org.benford.BenfordSeries;

public abstract class Calculator {

  private static final double EPS = 0.001;

  final double[] digitDistribution;
  final int count;

  public Calculator(double[] digitDistribution, int count) {
    checkDigitDistribution(digitDistribution);
    this.digitDistribution = digitDistribution;
    this.count = count;
  }

  public Calculator(BenfordSeries benfordSeries) {
    this(benfordSeries.getDigitDistribution(), benfordSeries.getCount());
  }

  abstract ResultHandler createScoreHandler(double[] score);

  abstract Double calculateScore(Double actual, Double expected);

  public ResultHandler calculateResult(double[] expected) {
    double[] score = new double[10];

    for (int i = 0; i < digitDistribution.length; i++) {
      score[i] = calculateScore(digitDistribution[i], expected[i]);
    }

    return createScoreHandler(score);
  }

  private void checkDigitDistribution(double[] digitDistribution) {
    if (digitDistribution.length != 10) {
      throw new IllegalArgumentException("digits distribution length must be 10");
    }

    double tot = 0;
    for (double d : digitDistribution) {
      if (d > 1.0) {
        throw new IllegalArgumentException("All the digits must be less then 1.0");
      }
      tot += d;
    }

    if (Math.abs(tot - 1.0) > EPS) {
      throw new IllegalArgumentException("The sum of all the distribution must be 1.0");
    }
  }
}
