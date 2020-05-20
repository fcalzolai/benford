package org.benford2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenfordCalculatorTest {

  private static final double DELTA = 0.07;

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

  private static final double[] TEST_1 = new double[]{
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

  private BenfordCalculator benfordCalculator;

  public BenfordCalculatorTest() {
    benfordCalculator = new BenfordCalculator(TEST_1);
  }

  @Test
  void getDigitDistribution() {
    double[] digitDistribution = benfordCalculator.getDigitDistribution();
    Assertions.assertArrayEquals(EXPECTED_DIGIT_DISTRIBUTION, digitDistribution, DELTA);
  }

  @Test
  void getCount() {
    int count = benfordCalculator.getCount();
    assertEquals(2051, count);
  }

  @Test
  void getZscoreSecondDigit() {
    double[] digitDistribution = benfordCalculator.getZscoreSecondDigit();
    Assertions.assertArrayEquals(EXPECTED_Z_SCORE, digitDistribution, DELTA);
  }
}