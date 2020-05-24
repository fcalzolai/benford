package org.benford.score;

import org.benford.Series;

public abstract class ScoreHandler extends Series {

  public ScoreHandler(double[] series) {
    super(series);
  }

  protected abstract double get95Accuracy();

  protected abstract double get99Accuracy();

  public int valueNotBenfordDistributedIn95() {
    return valueNotIn(get95Accuracy());
  }

  public int valueNotBenfordDistributedIn99() {
    return valueNotIn(get99Accuracy());
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
