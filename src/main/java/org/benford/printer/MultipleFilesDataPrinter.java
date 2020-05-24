package org.benford.printer;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordSeries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

import static java.io.File.separator;
import static org.benford.factory.BenfordSeriesFactory.getBenfordDataPrinters;

public class MultipleFilesDataPrinter {

  private static final String OUTPUT_PREFIX = "Output_";
  private static final String SUMMARY_FILE_NAME = "Summary.csv";

  private final String input;
  private final String output;
  private LinkedList<AggregateDataPrinter> aggregateDataPrinters;

  public MultipleFilesDataPrinter(String input, String output) throws IOException {
    this.input = input;
    ClassLoader classLoader = MultipleFilesDataPrinter.class.getClassLoader();
    String inputPath = classLoader.getResource(input).getPath();
    this.output = Paths.get(inputPath).toAbsolutePath() + separator + output + separator;
    Files.createDirectories(Paths.get(this.output));
  }

  public void computeData() throws IOException, CsvValidationException {
    System.out.println("Calculating data ... ");
    HashMap<String, BenfordDataPrinter> dataPrinters = getBenfordDataPrinters(input, 1, 4);
    aggregateDataPrinters = new LinkedList<>();
    dataPrinters.forEach((name, bdp) -> {
      System.out.print("Saving data for source file: " + name);
      saveAggregateData(name, bdp);
      writeToFile(name, bdp);
      System.out.println(" Done");
    });

    writeAggregateDataToFile();
  }

  private void writeAggregateDataToFile() {
    File newFile = new File(output + OUTPUT_PREFIX + SUMMARY_FILE_NAME);
    FileWriter fw = null;
    try {
      fw = new FileWriter(newFile);
      fw.write(AggregateDataPrinter.COLUMN_NAMES);
      for (AggregateDataPrinter zsp : aggregateDataPrinters) {
        fw.write(zsp.toCsv());
      }
    } catch (IOException e) {
      System.err.println("Unable to create new file " + newFile.getPath());
    } finally {
      closeFileWriter(fw);
    }
  }

  private void saveAggregateData(String name, BenfordDataPrinter bdp) {
    BenfordSeries benfordSeries = bdp.getBenfordSeries();
    aggregateDataPrinters.add(new AggregateDataPrinter(name, benfordSeries));
  }

  private void writeToFile(String name, BenfordDataPrinter bdp) {
    File newFile = new File(output + name);
    FileWriter fw = null;
    try {
      fw = new FileWriter(newFile);
      fw.write(bdp.toCsv());
    } catch (IOException e) {
      System.err.println("Unable to create new file " + newFile.getPath());
    } finally {
      closeFileWriter(fw);
    }
  }

  private void closeFileWriter(FileWriter fw) {
    if (fw != null) {
      try {
        fw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
