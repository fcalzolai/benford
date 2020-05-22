package org.benford.loader;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordSeries;
import org.benford.ZScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLoaderTest {

  private static final String WID_IT_PARTIAL = "stats/WID_Data_19052020-184437.csv";
  private static final String WID_IT_ALL = "stats/WID_data_IT.csv";
  private static final String FIBONACCI = "stats/fibonacci_300.csv";
  private static final double[] EXPECTED_FIBONACCI_SERIES = new double[]{
          0,
          96,
          55,
          40,
          28,
          26,
          20,
          18,
          17,
          14
  };

  @Test
  void createBenfordDistribution() throws IOException, CsvValidationException {
    ZScore zscore = getZScore(WID_IT_PARTIAL, 5, 2);
    System.out.println(zscore);
  }

  @Test
  void createBenfordDistribution2() throws IOException, CsvValidationException {
    ZScore zscore = getZScore(WID_IT_ALL, 1, 4);
    System.out.println(zscore);
  }

  @Test
  void createFibonacciBenfordDistribution() throws IOException, CsvValidationException {
    BenfordSeries benfordSeries = getBenfordSeries(FIBONACCI, 0, 0);
    Assertions.assertArrayEquals(EXPECTED_FIBONACCI_SERIES, benfordSeries.getSeries(), 0.0);
    assertEquals(0, benfordSeries.getZscoreFirstDigit().valueNotBenfordDistributedIn95());
    assertEquals(0, benfordSeries.getZscoreFirstDigit().valueNotBenfordDistributedIn99());
  }

  private ZScore getZScore(String path, int skipLine, int column) throws IOException, CsvValidationException {
    return getBenfordSeries(path, skipLine, column)
            .getZscoreFirstDigit();
  }

  private BenfordSeries getBenfordSeries(String path, int skipLine, int column) throws IOException, CsvValidationException {
    Reader reader = getReader(path);
    FileLoader loader = new FileLoader(reader, skipLine, column);
    return loader.createBenfordSeries();
  }

  private Reader getReader(String path) throws FileNotFoundException {
    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource(path);
    File file = new File(url.getFile());
    return new FileReader(file);
  }

}