package org.benford.data;

public class WikiValues implements TestValues {

  private static final double PERCENTAGE_DELTA = 0.001;

  private static final double[] EXPECTED_DIGIT_DISTRIBUTION = new double[]{
          0.0,
          0.433,
          0.117,
          0.15,
          0.1,
          0.067,
          0.017,
          0.033,
          0.083,
          0.0
  };

  private static final double[] VALUES = new double[]{
          0,
          26,
          7,
          9,
          6,
          4,
          1,
          2,
          5,
          0
  };

  @Override
  public double[] getValues() {
    return VALUES;
  }

  @Override
  public double[] getExpectedZScore() {
    throw new RuntimeException("Not implemented");
  }

  @Override
  public double[] getExpectedDigitDistribution() {
    return EXPECTED_DIGIT_DISTRIBUTION;
  }

  @Override
  public double getPercentageDelta() {
    return PERCENTAGE_DELTA;
  }

  @Override
  public double getZScoreDelta() {
    throw new RuntimeException("Not implemented");
  }
}
