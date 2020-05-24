package org.benford.factory;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordSeries;
import org.benford.loader.FileLoader;
import org.benford.printer.BenfordDataPrinter;
import org.benford.score.Calculator;
import org.benford.score.ChiSquareCalculator;
import org.benford.score.ZScoreCalculator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class BenfordSeriesFactory {

  private static final String INPUT_FILE_EXTENSION = ".csv";

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

  public static Function<BenfordSeries, Collection<Calculator>> getCalculatorProducer() {
    return (BenfordSeries bn) -> {
      Set<Calculator> calculators = new HashSet<>();
      calculators.add(new ZScoreCalculator(bn));
      calculators.add(new ChiSquareCalculator(bn));
      return calculators;
    } ;
  }

  public static boolean validateFile(File file) {
    return file.isFile() && file.getName().endsWith(INPUT_FILE_EXTENSION);
  }
}
