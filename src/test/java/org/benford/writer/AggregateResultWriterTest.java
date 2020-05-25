package org.benford.writer;

import org.benford.BenfordSeries;
import org.benford.data.*;
import org.benford.score.BenfordResultCalculator;
import org.benford.score.ResultHandler;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AggregateResultWriterTest {

  private static final String FILE_NAME = "fileName";
  private static final double DELTA = 0.00001;

  private AggregateResultWriter getWriter(TestValues tv) {
    BenfordSeries bs = new BenfordSeries(tv.getValues());
    HashMap<String, BenfordResultCalculator> calculators = new HashMap<>();
    calculators.put(FILE_NAME, new BenfordResultCalculator(bs));
    return new AggregateResultWriter(calculators);
  }

  @Test
  void toCsv() {
    AggregateResultWriter arw = getWriter(new AimcValues());
    assertNotNull(arw.toCsv());
  }

  @Test
  void AimcValues() {
    AggregateResultWriter arw = getWriter(new AimcValues());

    HashMap<String, BenfordResultCalculator> calculators = arw.getCalculators();
    List<ResultHandler> handlers = calculators.get(FILE_NAME).getResultHandlers();

    assertIterableEquals(List.of(9, 9), getZScore(handlers));
    assertEquals(398.3611941, getChi(handlers), 0.1);
  }

  @Test
  void MunicipalityValues() {
    AggregateResultWriter arw = getWriter(new MunicipalityValues());

    HashMap<String, BenfordResultCalculator> calculators = arw.getCalculators();
    List<ResultHandler> handlers = calculators.get(FILE_NAME).getResultHandlers();

    assertIterableEquals(List.of(2, 1), getZScore(handlers));
    assertEquals(33.41144805, getChi(handlers), DELTA);
  }

  @Test
  void WikiPopulationValues() {
    AggregateResultWriter arw = getWriter(new WikiPopulationValues());

    HashMap<String, BenfordResultCalculator> calculators = arw.getCalculators();
    List<ResultHandler> handlers = calculators.get(FILE_NAME).getResultHandlers();

    assertIterableEquals(List.of(0, 0), getZScore(handlers));
    assertEquals(6.59924813, getChi(handlers), DELTA);
  }

  @Test
  void WikiValues() {
    AggregateResultWriter arw = getWriter(new WikiValues());

    HashMap<String, BenfordResultCalculator> calculators = arw.getCalculators();
    List<ResultHandler> handlers = calculators.get(FILE_NAME).getResultHandlers();

    assertIterableEquals(List.of(1, 0), getZScore(handlers));
    assertEquals(11.97330837, getChi(handlers), DELTA);
  }

  private double getChi(List<ResultHandler> handlers) {
    return handlers.get(1).getAggregateValues().get(0).doubleValue();
  }

  private List<Number> getZScore(List<ResultHandler> handlers) {
    return handlers.get(0).getAggregateValues();
  }
}