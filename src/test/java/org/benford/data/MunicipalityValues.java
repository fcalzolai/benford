package org.benford.data;

public class MunicipalityValues implements TestValues {

  // https://www.scielo.br/scielo.php?script=sci_arttext&pid=S1808-23862018000400331

  private static final double PERCENTAGE_DELTA = 0.005;
  private static final double ZSCORE_DELTA = 0.12;

  private static final double[] EXPECTED_Z_SCORE = new double[]{
          0.0,
          0.408,
          3.456,
          0.038,
          0.342,
          0.068,
          22.353,
          1.632,
          1.910
  };

  private static final double[] EXPECTED_DIGIT_DISTRIBUTION = new double[]{
          0.0,
          0.2918100482,
          0.1555402615,
          0.123193393,
          0.09222298692,
          0.07708189952,
          0.09910529938,
          0.06607019959,
          0.05918788713,
          0.03578802478
  };

  private static final double[] VALUES = new double[]{
          0,
          424,
          226,
          179,
          134,
          112,
          144,
          96,
          86,
          52
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
