package org.benford.factory;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordSeries;
import org.benford.ZScore;
import org.benford.loader.FileLoader;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class BenfordSeriesFactory {

  public static BenfordSeries getBenfordSeries(String path, int skipLine, int column)
          throws IOException, CsvValidationException {
    Reader reader = getReader(path);
    FileLoader loader = new FileLoader(reader, skipLine, column);
    return loader.createBenfordSeries();
  }

  public static Reader getReader(String path) throws FileNotFoundException {
    ClassLoader classLoader = BenfordSeriesFactory.class.getClassLoader();
    URL url = classLoader.getResource(path);
    File file = new File(url.getFile());
    return new FileReader(file);
  }

  public static HashMap<String, ZScore> getZScores(String path, int skipLine, int column)
          throws IOException, CsvValidationException {
    ClassLoader classLoader = BenfordSeriesFactory.class.getClassLoader();
    URL url = classLoader.getResource(path);
    File[] files = new File(url.getFile()).listFiles();
    HashMap<String, ZScore> readers = new HashMap<>();
    for (File file: files) {
      long start = System.currentTimeMillis();
      FileReader reader = new FileReader(file);
      FileLoader loader = new FileLoader(reader, skipLine, column);
      BenfordSeries benfordSeries = loader.createBenfordSeries();
      readers.put(file.getName(), benfordSeries.getZscoreFirstDigit());
      System.out.println(
              file.getName() +
                      " count [" + benfordSeries.getCount()+ "] " +
                      "in " + (System.currentTimeMillis() - start) +  " millis.");
    }
    return readers;
  }

  public static ZScore getZScore(String path, int skipLine, int column) throws IOException, CsvValidationException {
    return getBenfordSeries(path, skipLine, column)
            .getZscoreFirstDigit();
  }

}
