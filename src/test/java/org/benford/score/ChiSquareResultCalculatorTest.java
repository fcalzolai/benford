package org.benford.score;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.benford.BenfordConst.FIRST_DIGIT_DISTRIBUTION;

class ChiSquareResultCalculatorTest {

  private static final double[] DIGIT_DISTRIBUTION = new double[]{
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


  private static final double[] EXPECTED_CHI_SQUARE = new double[]{
          Double.NaN,
          0.4103109803,
          3.484843823,
          0.03543729372,
          0.3293740281,
          0.08087098744,
          22.44539958,
          1.635076914,
          1.834055022,
          3.156079415
  };

  private static final int COUNT = 1453;

  private static final ChiSquareCalculator CHI_SQUARE_CALCULATOR = new ChiSquareCalculator(COUNT, DIGIT_DISTRIBUTION);

  private static final Double DELTA = 0.0001;

  @Test
  void calculateScore() {
    Double chiSquare = CHI_SQUARE_CALCULATOR.calculateScore(DIGIT_DISTRIBUTION[1], FIRST_DIGIT_DISTRIBUTION[1]);
    Assertions.assertEquals(0.4103109803, chiSquare, DELTA);
  }

  @Test
  void calculateResult() {
    ResultHandler res = CHI_SQUARE_CALCULATOR.calculateResult(FIRST_DIGIT_DISTRIBUTION);
    Assertions.assertArrayEquals(EXPECTED_CHI_SQUARE, res.getSeries(), DELTA);
  }
}