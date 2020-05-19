package org.benford.loader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordDistribution;
import org.benford.BenfordSeries;

import java.io.IOException;
import java.io.Reader;

public class FileLoader {

  private static final int ASCII_ZERO = 48;

  private final Reader reader;
  private final int skipLine;
  private final int column;

  public FileLoader(Reader reader, int skipLine, int column) {
    this.reader = reader;
    this.skipLine = skipLine;
    this.column = column;
  }

  public BenfordDistribution createBenfordDistribution() throws IOException, CsvValidationException {
    CSVReader csvReader = getReader(reader);
    double[] series = getSeries(csvReader);
    BenfordSeries benfordSeries = new BenfordSeries(series);
    return benfordSeries.getDistribution();
  }

  private CSVReader getReader(Reader reader) {
    CSVParser parser = new CSVParserBuilder()
            .withSeparator(';')
            .withIgnoreQuotations(false)
            .build();

    return new CSVReaderBuilder(reader)
            .withSkipLines(skipLine)
            .withCSVParser(parser)
            .build();
  }

  private double[] getSeries(CSVReader csvReader) throws IOException, CsvValidationException {
    double[] series = new double[10];
    String[] line;
    while ((line = csvReader.readNext()) != null) {
      String number = line[column];
      int digit = getFirstNotZero(number);
      if (digit >= 0) {
        series[digit]++;
      }
    }
    reader.close();
    csvReader.close();

    return series;
  }

  private int getFirstNotZero(String number) {
    byte[] bytes = number.getBytes();
    for (byte b: bytes) {
      if (isNotZero(b)) {
        return b - ASCII_ZERO;
      }
    }
    return -1;
  }

  private boolean isNotZero(byte b) {
    return 49 <= b && b <= 57;
  }

}
