package org.benford.score;

import static org.benford.BenfordConst.CHI_SQUARE_95;
import static org.benford.BenfordConst.CHI_SQUARE_99;

public class ChiSquareResult extends ScoreHandler {

  public ChiSquareResult(double[] series) {
    super(series);
  }

  protected double get95Accuracy() {
    return CHI_SQUARE_95;
  }

  protected double get99Accuracy() {
    return CHI_SQUARE_99;
  }

}
