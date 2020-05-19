package org.benford;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BenfordDistributionTest {

  private static final double DELTA = 0.07;

  private static final SecondDigitDistribution SECOND_DIGIT_DISTRIBUTION = new SecondDigitDistribution();

  private static final DoubleSeries EXPECTED_Z_SCORE = new DoubleSeries(new double[]{
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
  });

  private static final BenfordDistribution BENFORD_DISTRIBUTION = new BenfordDistribution(2051, new double[]{
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
  });

  @Test
  void getZscore() {
    DoubleSeries zscore = BENFORD_DISTRIBUTION.getZscore(SECOND_DIGIT_DISTRIBUTION);

    assertArrayEquals(EXPECTED_Z_SCORE.getSeries(), zscore.getSeries(), DELTA);
  }
}