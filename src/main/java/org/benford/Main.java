package org.benford;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.writer.MultipleFilesResultsWriter;

import java.io.IOException;

public class Main {

  private static final String INPUT_WID_FILES = "stats/";
  private static final String OUTPUT_WID_FILES = "output";

  public static void main(String[] args) throws IOException, CsvValidationException {
    MultipleFilesResultsWriter wdp = new MultipleFilesResultsWriter(INPUT_WID_FILES, OUTPUT_WID_FILES);
    wdp.computeData();
  }
}
