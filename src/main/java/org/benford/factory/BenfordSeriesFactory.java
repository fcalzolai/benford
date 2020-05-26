package org.benford.factory;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordSeries;
import org.benford.reader.FileLoader;
import org.benford.score.BenfordResultCalculator;
import org.benford.writer.BenfordResultWriter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BenfordSeriesFactory {

  private static final String WID_DATA_FILE_PATTERN = "WID_data_.*.csv";

  public static HashMap<String, BenfordResultCalculator> getBenfordDataCalculators(String path, int skipLine, int column)
          throws IOException, CsvValidationException {
    ClassLoader classLoader = BenfordSeriesFactory.class.getClassLoader();
    URL url = classLoader.getResource(path);
    File[] files = new File(url.getFile()).listFiles();
    HashMap<String, BenfordResultCalculator> dataPrinterHashMap = new HashMap<>();
    for (File file : files) {
      if (validateFile(file)) {
        System.out.print("Calculating results for file: " + file.getName());
        FileReader reader = new FileReader(file);
        FileLoader loader = new FileLoader(reader, skipLine, column);
        BenfordSeries benfordSeries = loader.createBenfordSeries();
        BenfordResultCalculator brc = new BenfordResultCalculator(benfordSeries);
        dataPrinterHashMap.put(file.getName(), brc);
        System.out.println(" Done");
      }
    }
    return dataPrinterHashMap;
  }

  public static HashMap<String, BenfordResultWriter> createBenFordDataPrinter(HashMap<String, BenfordResultCalculator> calculatorHashMap) {
    HashMap<String, BenfordResultWriter> benfordDataPrinter = new HashMap<>();
    calculatorHashMap.forEach((name, brc) -> benfordDataPrinter.put(name, new BenfordResultWriter(brc)));
    return benfordDataPrinter;
  }

  public static boolean validateFile(File file) {
    Pattern pattern = Pattern.compile(WID_DATA_FILE_PATTERN);
    Matcher matcher = pattern.matcher(file.getName());
    return matcher.find();
  }
}
