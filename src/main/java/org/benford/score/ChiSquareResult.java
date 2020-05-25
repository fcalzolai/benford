package org.benford.score;

import java.util.List;

public class ChiSquareResult extends ResultHandler {

  private static final double CHI_SQUARE_95 = 15.51;
  private static final double CHI_SQUARE_99 = 20.09;
  private static final String CHI_SQUARE_COLUMN_NAME = "Chi Square";
  private static final List<String> AGGREGATE_COLUMNS_NAMES = List.of(
          "Chi Square",
          "Chi Square 95%",
          "Chi Square 99%");

  private final double chiSquare;

  public ChiSquareResult(double[] series) {
    super(series);
    chiSquare = calcChiSquare(series);
  }

  @Override
  public List<Number> getAggregateValues() {
    return List.of(chiSquare, CHI_SQUARE_95, CHI_SQUARE_99);
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
