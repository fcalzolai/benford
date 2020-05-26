package org.benford.score;

import org.junit.jupiter.api.Test;

import static org.benford.score.ChiSquareUtils.pochisq;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChiSquareUtilsTest {

  private static final double DELTA = 0.00005;

  @Test
  public void testPiValueOfChi2() {
    assertEquals(0.1282, pochisq(2.314, 1), DELTA);
    assertEquals(0.3144, pochisq(2.314, 2), DELTA);
    assertEquals(0, pochisq(17, 1), DELTA);
    assertEquals(0.3173, pochisq(1, 1), DELTA);
    assertEquals(0.2231, pochisq(3, 2), DELTA);

    assertEquals(0.950143, pochisq(2.73, 8), DELTA);
    assertEquals(0.899964, pochisq(3.49, 8), DELTA);
    assertEquals(0.800363, pochisq(4.59, 8), DELTA);
    assertEquals(0.699714, pochisq(5.53, 8), DELTA);
    assertEquals(0.500432, pochisq(7.34, 8), DELTA);
    assertEquals(0.300343, pochisq(9.52, 8), DELTA);
    assertEquals(0.200005, pochisq(11.03, 8), DELTA);
    assertEquals(0.100049, pochisq(13.36, 8), DELTA);
    assertEquals(0.049955, pochisq(15.51, 8), DELTA);
    assertEquals(0.010001, pochisq(20.09, 8), DELTA);
    assertEquals(0.001002, pochisq(26.12, 8), DELTA);
  }

}