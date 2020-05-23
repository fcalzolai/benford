package org.benford.score;

import org.benford.BenfordConst;
import org.benford.Series;

public class ChiSquare extends Series {

  public ChiSquare(double[] series) {
    super(series);
  }

  public int valueNotBenfordDistributedIn95() {
    return valueNotIn(BenfordConst.CHI_SQUARE_95);
  }

  public int valueNotBenfordDistributedIn99() {
    return valueNotIn(BenfordConst.CHI_SQUARE_99);
  }

  private int valueNotIn(double limit) {
    int res = 0;
    for (double d: series) {
      if (d > limit) {
        res++;
      }
    }
    return res;
  }
}
