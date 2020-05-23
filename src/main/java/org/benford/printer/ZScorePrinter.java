package org.benford.printer;

import lombok.Getter;
import org.benford.zscore.ZScore;

@Getter
public class ZScorePrinter {

  public static final String COLUMN_NAMES = "Source file, # digit not in Benford distribution 95%, # digit not in Benford distribution 99%\n";
  private static final String SEP = ",";

  private final String file;
  private final int isNotBenford95;
  private final int isNotBenford99;

  public ZScorePrinter(String file, ZScore zScore) {
    this.file = file;
    this.isNotBenford95 = zScore.valueNotBenfordDistributedIn95();
    this.isNotBenford99 = zScore.valueNotBenfordDistributedIn99();
  }

  public String toCsv() {
    return file + SEP +
            isNotBenford95 + SEP +
            isNotBenford99 +
            "\n";
  }
}
