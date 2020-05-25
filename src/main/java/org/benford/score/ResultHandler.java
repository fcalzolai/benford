package org.benford.score;

import org.benford.Series;

import java.util.List;

public abstract class ResultHandler extends Series {

  public ResultHandler(double[] series) {
    super(series);
  }

  public abstract List<Number> getAggregateValues();

  public abstract String getColumnName();

  public abstract List<String> getAggregateColumnsNames();
}
