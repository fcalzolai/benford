package org.benford.printer;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryCalculator {

  private static final String COUNTRIES_FILE = "stats/WID_countries.csv";
  private static CountryCalculator instance;

  private final HashMap<String, String> countriesMap;

  private CountryCalculator() {
    countriesMap = calculateCountry();
  }

  public static CountryCalculator getInstance(){
    if (instance == null) {
      instance = new CountryCalculator();
    }

    return instance;
  }

  public static String getCountry(String fileName) {
    String regionCode = getRegionCode(fileName);
    return getInstance().countriesMap.getOrDefault(regionCode, fileName);
  }

  private HashMap<String, String> calculateCountry() {
    CSVReader csvReader = null;
    try {
      csvReader = getCsvReader();
      return getCountryMap(csvReader);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Unable to build countries map from file: " + COUNTRIES_FILE);
      closeCsvReader(csvReader);
      throw new RuntimeException(e);
    }
  }

  private static String getRegionCode(String fileName) {
    Pattern pattern = Pattern.compile("WID_data_(.*?).csv");
    Matcher matcher = pattern.matcher(fileName);
    if (matcher.find()) {
      return matcher.group(1);
    } else {
      return null;
    }
  }

  private void closeCsvReader(CSVReader csvReader) {
    if (csvReader != null) {
      try {
        csvReader.close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  private CSVReader getCsvReader() throws FileNotFoundException {
    URL url = ZScorePrinter.class.getClassLoader().getResource(COUNTRIES_FILE);

    CSVParser parser = new CSVParserBuilder()
            .withSeparator(';')
            .withIgnoreQuotations(false)
            .build();

    return new CSVReaderBuilder(new FileReader(url.getFile()))
            .withSkipLines(1)
            .withCSVParser(parser)
            .build();
  }

  private HashMap<String, String> getCountryMap(CSVReader csvReader) throws IOException, CsvValidationException {
    HashMap<String, String> map = new HashMap<>();
    String[] line;
    while ((line = csvReader.readNext()) != null) {
      map.put(line[0], "\"" + line[2] + "\"");
    }

    return map;
  }

}
