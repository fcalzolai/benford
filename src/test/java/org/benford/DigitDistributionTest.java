package org.benford;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitDistributionTest {

  private static final SecondDigitDistribution SECOND_DIGIT_DISTRIBUTION = new SecondDigitDistribution();

  private static final DigitSeries EXPECTED = new DigitSeries(new Double[]{
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

  private BenfordDistribution benfordDistribution;

  public DigitDistributionTest() {
    benfordDistribution = new BenfordDistribution(2051, new Double[]{
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
  }

  @Test
  void getZscore() {
    DigitSeries zscore = benfordDistribution.getZscore(SECOND_DIGIT_DISTRIBUTION);

    for (int i = 0; i < EXPECTED.series.length; i++) {
      assertEquals(EXPECTED.series[i], zscore.getSeries()[i], 0.07);
    }
    System.out.println(Arrays.toString(zscore.series));
    System.out.println(Arrays.toString(EXPECTED.series));
  }
}