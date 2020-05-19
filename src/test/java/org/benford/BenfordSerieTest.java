package org.benford;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BenfordSerieTest {

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

  private static final BenfordSeries BENFORD_SERIES = new BenfordSeries(new double[]{
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
  });

  @Test
  void getZscore() {
    DoubleSeries zscore = BENFORD_SERIES.getZscore(SECOND_DIGIT_DISTRIBUTION);
    assertArrayEquals(EXPECTED_Z_SCORE.getSeries(), zscore.getSeries(), DELTA);
  }
}