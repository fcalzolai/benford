package org.benford.writer;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.score.BenfordResultCalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static java.io.File.separator;
import static org.benford.factory.BenfordSeriesFactory.createBenFordDataPrinter;
import static org.benford.factory.BenfordSeriesFactory.getBenfordDataCalculators;

public class MultipleFilesResultsWriter {

  private static final String OUTPUT_PREFIX = "Results_";
  private static final String SUMMARY_FILE_NAME = "Summary.csv";

  private final String input;
  private final String output;

  public MultipleFilesResultsWriter(String input, String output) throws IOException {
    this.input = input;
    ClassLoader classLoader = MultipleFilesResultsWriter.class.getClassLoader();
    String inputPath = classLoader.getResource(input).getPath();
    this.output = Paths.get(inputPath).toAbsolutePath() + separator + output + separator;
    Files.createDirectories(Paths.get(this.output));
  }

  public void computeData() throws IOException, CsvValidationException {
    System.out.println("Calculating data ... ");
    HashMap<String, BenfordResultCalculator> calculators = getBenfordDataCalculators(input, 1, 4);
    saveBenfordResultToFile(calculators);
    saveAggregateResultToFile(calculators);
  }

  private void saveBenfordResultToFile(HashMap<String, BenfordResultCalculator> calculators) {
    HashMap<String, BenfordResultWriter> dataPrinters = createBenFordDataPrinter(calculators);
    dataPrinters.forEach((name, bdp) -> {
      System.out.print("Saving data for source file: " + name);
      writeResultToFile(name, bdp);
      System.out.println(" Done");
    });
  }

  private void saveAggregateResultToFile(HashMap<String, BenfordResultCalculator> calculators) {
    File newFile = new File(output + OUTPUT_PREFIX + SUMMARY_FILE_NAME);
    FileWriter fw = null;
    try {
      fw = new FileWriter(newFile);
      fw.write(new AggregateResultWriter(calculators).toCsv());
    } catch (IOException e) {
      System.err.println("Unable to create new file " + newFile.getPath());
    } finally {
      closeFileWriter(fw);
    }
  }

  private void writeResultToFile(String name, BenfordResultWriter bdp) {
    File newFile = new File(output + OUTPUT_PREFIX + name);
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
