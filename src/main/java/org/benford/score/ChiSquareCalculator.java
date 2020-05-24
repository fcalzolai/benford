package org.benford.score;

import org.benford.BenfordSeries;

public class ChiSquareCalculator extends Calculator {

  public ChiSquareCalculator(int count, double[] digitDistribution) {
    super(digitDistribution, count);
  }

  public ChiSquareCalculator(BenfordSeries benfordSeries) {
    super(benfordSeries);
  }

  @Override
  protected ResultHandler createScoreHandler(double[] score) {
    return new ChiSquareResult(score);
  }

  @Override
  public Double calculateScore(Double actual, Double expected) {
    double actualVal = actual * count;
    double expectedVal = expected * count;
    double numerator = Math.pow((actualVal - expectedVal), 2.0);
    return numerator / expectedVal;
  }
}