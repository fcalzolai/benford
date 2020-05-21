package org.benford2;

public class ZScore extends Series {

  public ZScore(double[] series) {
    super(series);
  }

  public int valueNotBenfordDistributedIn95() {
    return valueNotIn(BenfordConst.VALUES_95);
  }

  public int valueNotBenfordDistributedIn99() {
    return valueNotIn(BenfordConst.VALUES_99);
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
