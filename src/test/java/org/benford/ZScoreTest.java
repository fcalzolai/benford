package org.benford;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZScoreTest {

  private static final ZScore Z_SCORE = new ZScore(new double[]{
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

  @Test
  void valueNotBenfordDistributedIn95() {
    assertEquals(3, Z_SCORE.valueNotBenfordDistributedIn95());
  }

  @Test
  void valueNotBenfordDistributedIn99() {
    assertEquals(2, Z_SCORE.valueNotBenfordDistributedIn99());
  }
}