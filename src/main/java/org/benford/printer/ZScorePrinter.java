package org.benford.printer;

import lombok.Getter;
import org.benford.BenfordSeries;
import org.benford.zscore.ZScore;

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
    ZScore zScore = benfordSeries.getZscoreFirstDigit();
    this.totalLines = benfordSeries.getCount();
    this.isNotBenford95 = zScore.valueNotBenfordDistributedIn95();
    this.isNotBenford99 = zScore.valueNotBenfordDistributedIn99();
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
}
