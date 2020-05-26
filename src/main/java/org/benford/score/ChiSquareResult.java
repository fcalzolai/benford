package org.benford.score;

import java.util.List;

import static org.benford.score.ChiSquareUtils.pochisq;

public class ChiSquareResult extends ResultHandler {

  private static final String CHI_SQUARE_COLUMN_NAME = "Chi Square";
  private static final List<String> AGGREGATE_COLUMNS_NAMES = List.of("P value");
  private static final int DEGREE_OF_FREEDOM = 8;

  private final double chiSquare;
  private final double pvalue;

  public ChiSquareResult(double[] series) {
    super(series);
    chiSquare = calcChiSquare(series);
    pvalue = pochisq(chiSquare, DEGREE_OF_FREEDOM);
  }

  @Override
  public List<Number> getAggregateValues() {
    return List.of(pvalue);
  }

  @Override
  public String getColumnName() {
    return CHI_SQUARE_COLUMN_NAME;
  }

  @Override
  public List<String> getAggregateColumnsNames() {
    return AGGREGATE_COLUMNS_NAMES;
  }

  public double getChiSquare() {
    return chiSquare;
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
