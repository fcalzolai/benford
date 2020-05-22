package org.benford;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.printer.WidDataPrinter;

import java.io.IOException;

public class Main {

  private static final String INPUT_WID_FILES = "stats/";
  private static final String OUTPUT_WID_FILES = "output";

  public static void main(String[] args) throws IOException, CsvValidationException {
    WidDataPrinter wdp = new WidDataPrinter(INPUT_WID_FILES, OUTPUT_WID_FILES);
    wdp.computeData();
  }
}
