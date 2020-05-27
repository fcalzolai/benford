package org.benford.score;

import java.util.List;

public class ZScoreResult extends ResultHandler {

  private static final double ZSCORE_95 = 1.96;
  private static final double ZSCORE_99 = 2.58;
  private static final String ZSCORE_COLUMN_NAME = "ZScore";
  private static final List<String> AGGREGATE_COLUMNS_NAMES = List.of(
          "# digits not in Benford distribution 95%",
          "# digits not in Benford distribution 99%");

  public ZScoreResult(double[] series) {
    super(series);
  }

  @Override
  public String getColumnName() {
    return ZSCORE_COLUMN_NAME;
  }

  @Override
  public List<String> getAggregateColumnsNames() {
    return AGGREGATE_COLUMNS_NAMES;
  }

  @Override
  public List<Number> getAggregateValues() {
    return List.of(valueNotIn(ZSCORE_95),  valueNotIn(ZSCORE_99));
  }

  private int valueNotIn(double limit) {
    int res = 0;
    for (double d : series) {
      if (d > limit) {
        res++;
      }
    }
    return res;
  }
}
