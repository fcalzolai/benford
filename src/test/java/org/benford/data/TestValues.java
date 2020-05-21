package org.benford.data;

public interface TestValues {

  double[] getValues();
  double[] getExpectedZScore();
  double[] getExpectedDigitDistribution();
  double getPercentageDelta();
  double getZScoreDelta();
}
