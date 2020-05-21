package org.benford2.loader;

import com.opencsv.exceptions.CsvValidationException;
import org.benford2.BenfordSeries;
import org.benford2.ZScore;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;

public class FileLoaderTest {

  private static final String PATH = "stats/WID_Data_19052020-184437.csv";
  private static final String PATH_2 = "stats/WID_data_IT.csv";

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

  private ZScore getZScore(String path, int skipLine, int column) throws IOException, CsvValidationException {
    Reader reader = getReader(path);
    FileLoader loader = new FileLoader(reader, skipLine, column);
    BenfordSeries benfordDistribution = loader.createBenfordSeries();
    return benfordDistribution.getZscoreFirstDigit();
  }

  private Reader getReader(String path) throws FileNotFoundException {
    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource(path);
    File file = new File(url.getFile());
    return new FileReader(file);
  }

}