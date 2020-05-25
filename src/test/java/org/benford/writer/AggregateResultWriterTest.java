package org.benford.writer;

import org.benford.BenfordSeries;
import org.benford.data.AimcValues;
import org.benford.score.BenfordResultCalculator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class AggregateResultWriterTest {

  private static final String FILE_NAME = "fileName";
  private static final AimcValues AIMC = new AimcValues();

  private final AggregateResultWriter arw;

  AggregateResultWriterTest() {
    BenfordSeries bs = new BenfordSeries(AIMC.getValues());
    HashMap<String, BenfordResultCalculator> calculators = new HashMap<>();
    calculators.put(FILE_NAME, new BenfordResultCalculator(bs));

    arw = new AggregateResultWriter(calculators);
  }

  @Test
  void toCsv() {
    String toCsv = arw.toCsv();
    System.out.println(toCsv);
  }

  @Test
  void getCalculators() {
//    HashMap<String, BenfordResultCalculator> calculators = arw.getCalculators();
//    BenfordResultCalculator calculator = calculators.get(FILE_NAME);
//    calculator.
  }
}