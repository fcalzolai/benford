package org.benford;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZScoreCalculatorTest {

  /*
  * Data source: https://www.researchgate.net/publication/330695071_APPLYING_BENFORD'S_LAW_BY_TESTING_THE_GOVERNMENT_MACROECONOMICS_DATA
  * */

  private static final double[] DIGIT_DISTRIBUTION = new double[]{
          0.0,
          0.431,
          0.245,
          0.0,
          0.039,
          0.064,
          0.044,
          0.069,
          0.049,
          0.059
  };

  private static final double[] EXPECTED_Z_SCORE = new double[]{
          Double.NaN,
          3.982,
          2.496,
          5.291,
          2.667,
          0.688,
          1.165,
          0.5,
          0.138,
          0.725
  };

  class MockBenfordSeries extends BenfordSeries {
    private final int count;
    public MockBenfordSeries(int count) {
      super(new double[10]);
      this.count = count;
    }

    @Override
    public int getCount() {
      return count;
    }

    @Override
    public double[] getDigitDistribution() {
      return DIGIT_DISTRIBUTION;
    }
  }

  @Test
  void ZScore_204() {
    MockBenfordSeries mbs = new MockBenfordSeries(204);
    ZScore zScore = mbs.getZscoreFirstDigit();
    System.out.println(zScore);
    Assertions.assertArrayEquals(EXPECTED_Z_SCORE, zScore.series, 0.181);
  }

  @Test
  void ZScore_408() {
    MockBenfordSeries mbs = new MockBenfordSeries(408);
    ZScore zScore = mbs.getZscoreFirstDigit();
    System.out.println(zScore);
    Assertions.assertArrayEquals(EXPECTED_Z_SCORE, zScore.series, 2.5);
  }

}