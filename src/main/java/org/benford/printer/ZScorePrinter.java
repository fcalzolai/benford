package org.benford.printer;

import lombok.Getter;
import org.benford.BenfordConst;
import org.benford.BenfordSeries;
import org.benford.score.ResultHandler;
import org.benford.score.ZScoreCalculator;

@Getter
public class ZScorePrinter {

  public static final String COLUMN_NAMES = "Source file, " +
          "Country, " +
          "# Lines, " +
          "# digit not in Benford distribution 95%, " +
          "# digit not in Benford distribution 99%\n";

  private static final String SEP = ",";

  private final String file;
  private final int isNotBenford95;
  private final int isNotBenford99;
  private final int totalLines;

  public ZScorePrinter(String file, BenfordSeries benfordSeries) {
    this.file = file;
    ResultHandler handler = getResultHandler(benfordSeries);
    this.totalLines = benfordSeries.getCount();
    this.isNotBenford95 = handler.valueNotBenfordDistributedIn95();
    this.isNotBenford99 = handler.valueNotBenfordDistributedIn99();
  }

  public String toCsv() {
    return file + SEP +
            getCountry(file) + SEP +
            totalLines + SEP +
            isNotBenford95 + SEP +
            isNotBenford99 +
            "\n";
  }

  private String getCountry(String file) {
    return CountryCalculator.getCountry(file);
  }

  private ResultHandler getResultHandler(BenfordSeries benfordSeries) {
    ZScoreCalculator calculator = new ZScoreCalculator(benfordSeries);
    return calculator.calculateResult(BenfordConst.FIRST_DIGIT_DISTRIBUTION);
  }
}
