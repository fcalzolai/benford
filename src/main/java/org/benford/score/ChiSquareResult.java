package org.benford.score;

import java.util.List;

public class ChiSquareResult extends ResultHandler {

  private static final String CHI_SQUARE_COLUMN_NAME = "Chi Square";
  private static final List<String> AGGREGATE_COLUMNS_NAMES = List.of(CHI_SQUARE_COLUMN_NAME);

  private final double chiSquare;

  public ChiSquareResult(double[] series) {
    super(series);
    chiSquare = calcChiSquare(series);
  }

  @Override
  public List<Number> getAggregateValues() {
    return List.of(chiSquare);
  }

  @Override
  public String getColumnName() {
    return CHI_SQUARE_COLUMN_NAME;
  }

  @Override
  public List<String> getAggregateColumnsNames() {
    return AGGREGATE_COLUMNS_NAMES;
  }

  private double calcChiSquare(double[] series) {
    double chi = 0;
    for (double v : series) {
      if (Double.isFinite(v)) {
        chi += v;
      }
    }
    return chi;
  }
}
