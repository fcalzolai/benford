package org.benford2.data;

public interface TestValues {

  double[] getValues();
  double[] getExpectedZScore();
  double[] getExpectedDigitDistribution();
  double getPercentageDelta();
  double getZScoreDelta();
}
