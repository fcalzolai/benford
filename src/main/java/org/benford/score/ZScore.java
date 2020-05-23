package org.benford.score;

import org.benford.BenfordConst;
import org.benford.Series;

public class ZScore extends Series {

  public ZScore(double[] series) {
    super(series);
  }

  public int valueNotBenfordDistributedIn95() {
    return valueNotIn(BenfordConst.ZSCORE_95);
  }

  public int valueNotBenfordDistributedIn99() {
    return valueNotIn(BenfordConst.ZSCORE_99);
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
