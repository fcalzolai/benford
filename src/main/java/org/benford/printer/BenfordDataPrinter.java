package org.benford.printer;

import lombok.Getter;
import org.benford.BenfordConst;
import org.benford.BenfordSeries;
import org.benford.score.ResultHandler;
import org.benford.score.ZScoreCalculator;

@Getter
public class BenfordDataPrinter {

  private static final String DEFAULT_SEP = ",";
  private static final boolean WITH_COLUMN_NAME = true;

  private final BenfordSeries benfordSeries;
  private final boolean withColumnName;
  private final String sep;

  public BenfordDataPrinter(BenfordSeries benfordSeries) {
    this(benfordSeries, WITH_COLUMN_NAME, DEFAULT_SEP);
  }

  public BenfordDataPrinter(BenfordSeries benfordSeries,
                            boolean withColumnName,
                            String sep) {
    this.benfordSeries = benfordSeries;
    this.withColumnName = withColumnName;
    this.sep = sep;
  }

  public String toCsv() {
    StringBuilder builder = new StringBuilder();

    if(withColumnName) {
      builder.append("digit,count,empirical,benford,zscore\n");
    }

    double[] series = benfordSeries.getSeries();
    double[] digitDistribution = benfordSeries.getDigitDistribution();
    double[] firstDigitDistribution = BenfordConst.FIRST_DIGIT_DISTRIBUTION;
    double[] zscoreFirstDigit = getZScoreFirstDigitDistribution(firstDigitDistribution);

    for (int i = 0; i < series.length; i++) {
      builder.append(i).append(sep)
              .append(series[i]).append(sep)
              .append(digitDistribution[i]).append(sep)
              .append(firstDigitDistribution[i]).append(sep)
              .append(zscoreFirstDigit[i]).append(sep)
              .append("\n");
    }

    return builder.toString();
  }

  private double[] getZScoreFirstDigitDistribution(double[] firstDigitDistribution) {
    ZScoreCalculator calculator = new ZScoreCalculator(benfordSeries);
    ResultHandler resultHandler = calculator.calculateResult(firstDigitDistribution);
    return resultHandler.getSeries();
  }
}
