package org.benford.writer;

import lombok.Getter;
import org.benford.score.BenfordResultCalculator;
import org.benford.BenfordSeries;

import java.util.stream.Collectors;

import static org.benford.BenfordConst.FIRST_DIGIT_DISTRIBUTION;

@Getter
public class BenfordResultWriter {

  private static final String DEFAULT_SEP = ",";
  private static final boolean WITH_COLUMN_NAME = true;

  private final BenfordResultCalculator benfordResultCalculator;
  private final boolean withColumnName;
  private final String sep;

  public BenfordResultWriter(BenfordResultCalculator benfordResultCalculator) {
    this(benfordResultCalculator, WITH_COLUMN_NAME, DEFAULT_SEP);
  }

  public BenfordResultWriter(BenfordResultCalculator benfordResultCalculator,
                             boolean withColumnName,
                             String sep) {
    this.benfordResultCalculator = benfordResultCalculator;
    this.withColumnName = withColumnName;
    this.sep = sep;
  }

  public String toCsv() {
    StringBuilder builder = new StringBuilder();

    if(withColumnName) {
      builder.append("digit,count,empirical,benford,");
      builder.append(benfordResultCalculator.getColumnsNames(sep));
      builder.append("\n");
    }

    BenfordSeries benfordSeries = benfordResultCalculator.getBenfordSeries();
    double[] series = benfordSeries.getSeries();
    double[] actualDigitDistribution = benfordSeries.getDigitDistribution();

    for (int i = 0; i < series.length; i++) {
      builder.append(i).append(sep)
              .append(series[i]).append(sep)
              .append(actualDigitDistribution[i]).append(sep)
              .append(FIRST_DIGIT_DISTRIBUTION[i]).append(sep)
              .append(getResultsValues(i))
              .append("\n");
    }

    return builder.toString();
  }

  private String getResultsValues(int i) {
    return benfordResultCalculator.getResultHandlers().stream()
            .map(rh -> Double.toString(rh.getSeries()[i]))
            .collect(Collectors.joining(sep));
  }
}
