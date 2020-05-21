package org.benford2.loader;

import com.opencsv.exceptions.CsvValidationException;
import org.benford2.BenfordSeries;
import org.benford2.ZScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;

public class FileLoaderTest {

  private static final String PATH = "stats/WID_Data_19052020-184437.csv";
  private static final String PATH_2 = "stats/WID_data_IT.csv";
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
    ZScore zscore = getZScore(PATH, 5, 2);
    System.out.println(zscore);
  }

  @Test
  void createBenfordDistribution2() throws IOException, CsvValidationException {
    ZScore zscore = getZScore(PATH_2, 1, 4);
    System.out.println(zscore);
  }

  @Test
  void createFibonacciBenfordDistribution() throws IOException, CsvValidationException {
    BenfordSeries benfordSeries = getBenfordSeries(FIBONACCI, 0, 0);
    double[] series = benfordSeries.getSeries();
    Assertions.assertArrayEquals(EXPECTED_FIBONACCI_SERIES, series, 0.0);
  }

  private ZScore getZScore(String path, int skipLine, int column) throws IOException, CsvValidationException {
    BenfordSeries benfordDistribution = getBenfordSeries(path, skipLine, column);
    return benfordDistribution.getZscoreFirstDigit();
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