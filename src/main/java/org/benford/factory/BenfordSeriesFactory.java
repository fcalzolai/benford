package org.benford.factory;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordSeries;
import org.benford.score.ZScoreResult;
import org.benford.loader.FileLoader;
import org.benford.printer.BenfordDataPrinter;

import java.io.*;
import java.net.URL;
import java.util.HashMap;

public class BenfordSeriesFactory {

  private static final String INPUT_FILE_EXTENSION = ".csv";

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

  public static HashMap<String, ZScoreResult> getZScores(String path, int skipLine, int column)
          throws IOException, CsvValidationException {
    ClassLoader classLoader = BenfordSeriesFactory.class.getClassLoader();
    URL url = classLoader.getResource(path);
    File[] files = new File(url.getFile()).listFiles();
    HashMap<String, ZScoreResult> readers = new HashMap<>();
    for (File file: files) {
      if (validateFile(file)) {
        FileReader reader = new FileReader(file);
        FileLoader loader = new FileLoader(reader, skipLine, column);
        BenfordSeries benfordSeries = loader.createBenfordSeries();
        readers.put(file.getName(), benfordSeries.getZscoreFirstDigit());
      }
    }
    return readers;
  }

  public static HashMap<String, BenfordDataPrinter> getBenfordDataPrinters(String path, int skipLine, int column)
          throws IOException, CsvValidationException {
    ClassLoader classLoader = BenfordSeriesFactory.class.getClassLoader();
    URL url = classLoader.getResource(path);
    File[] files = new File(url.getFile()).listFiles();
    HashMap<String, BenfordDataPrinter> readers = new HashMap<>();
    for (File file: files) {
      if (validateFile(file)) {
        System.out.print("Calculating results for file: " + file.getName());
        FileReader reader = new FileReader(file);
        FileLoader loader = new FileLoader(reader, skipLine, column);
        BenfordSeries benfordSeries = loader.createBenfordSeries();
        readers.put(file.getName(), new BenfordDataPrinter(benfordSeries));
        System.out.println(" Done");
      }
    }
    return readers;
  }

  private static boolean validateFile(File file) {
    return file.isFile() && file.getName().endsWith(INPUT_FILE_EXTENSION);
  }

  public static ZScoreResult getZScore(String path, int skipLine, int column) throws IOException, CsvValidationException {
    return getBenfordSeries(path, skipLine, column)
            .getZscoreFirstDigit();
  }

}
