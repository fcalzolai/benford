package org.benford;

import org.benford.score.ZScoreCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.benford.BenfordConst.FIRST_DIGIT_DISTRIBUTION;

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

  private static final double DELTA = 0.023;

  private final ZScoreCalculator zScoreCalculator = new ZScoreCalculator(204, DIGIT_DISTRIBUTION);

  @Test
  void ZScore_Series_204() {
    double[] zScore = zScoreCalculator.calculateZscore(FIRST_DIGIT_DISTRIBUTION);
    System.out.println(Arrays.toString(zScore));
    Assertions.assertArrayEquals(EXPECTED_Z_SCORE, zScore, DELTA);
  }

  @Test
  void ZScore() {
    Double actual = zScoreCalculator.calculateZscore(0.431, 0.301);
    Assertions.assertEquals(3.982, actual, DELTA);
    System.out.println(actual);
  }

}