package org.benford.score;

import org.benford.BenfordConst;

public class ZScoreResult extends ScoreHandler {

  public ZScoreResult(double[] series) {
    super(series);
  }

  @Override
  protected double get95Accuracy() {
    return BenfordConst.ZSCORE_95;
  }

  @Override
  protected double get99Accuracy() {
    return BenfordConst.ZSCORE_99;
  }
}
