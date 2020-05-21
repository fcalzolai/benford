package org.benford2.data;

public class AimcValues implements TestValues {

  private static final double PERCENTAGE_DELTA = 0.00005;
  private static final double ZSCORE_DELTA = 0.07;

  private static final double[] EXPECTED_Z_SCORE = new double[]{
          1.769923893,
          2.379742763,
          2.586727806,
          0.9643296328,
          0.0,
          0.0,
          1.762041594,
          1.519190068,
          3.149272937,
          0.4546957047
  };

  private static final double[] EXPECTED_DIGIT_DISTRIBUTION = new double[]{
          0.1073,
          0.1307,
          0.1268,
          0.0975,
          0.1,
          0.097,
          0.1043,
          0.0804,
          0.0683,
          0.0878
  };

  private static final double[] VALUES = new double[]{
          220,
          268,
          260,
          200,
          205,
          199,
          214,
          165,
          140,
          180
  };

  @Override
  public double[] getValues() {
    return VALUES;
  }

  @Override
  public double[] getExpectedZScore() {
    return EXPECTED_Z_SCORE;
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
    return ZSCORE_DELTA;
  }
}
