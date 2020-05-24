package org.benford.score;

import org.benford.BenfordSeries;

public class ZScoreCalculator extends Calculator {

  private final double continuityCorrectionFactor;

  public ZScoreCalculator(int count, double[] digitDistribution) {
    super(digitDistribution, count);
    this.continuityCorrectionFactor = 1/(2 * (double) count);
  }

  public ZScoreCalculator(BenfordSeries benfordSeries) {
    this(benfordSeries.getCount(), benfordSeries.getDigitDistribution());
  }

  @Override
  protected ScoreHandler createScoreHandler(double[] score) {
    return new ZScoreResult(score);
  }

  @Override
  public Double calculateScore(Double actual, Double expected) {
    double abs = Math.abs(actual - expected);
    double ccf = continuityCorrectionFactor < abs ? continuityCorrectionFactor : 0;
    Double numerator = abs - ccf;
    Double denominator = Math.sqrt((expected * (1 - expected)) / count);
    return numerator / denominator;
  }

}