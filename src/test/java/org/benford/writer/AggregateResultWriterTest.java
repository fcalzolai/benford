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
  private static final double DELTA = 0.0001;

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
    assertEquals(0.0, getPValue(handlers), DELTA);
  }

  @Test
  void MunicipalityValues() {
    AggregateResultWriter arw = getWriter(new MunicipalityValues());

    HashMap<String, BenfordResultCalculator> calculators = arw.getCalculators();
    List<ResultHandler> handlers = calculators.get(FILE_NAME).getResultHandlers();

    assertIterableEquals(List.of(2, 1), getZScore(handlers));
    assertEquals(0.000052, getPValue(handlers), DELTA);
  }

  @Test
  void WikiPopulationValues() {
    AggregateResultWriter arw = getWriter(new WikiPopulationValues());

    HashMap<String, BenfordResultCalculator> calculators = arw.getCalculators();
    List<ResultHandler> handlers = calculators.get(FILE_NAME).getResultHandlers();

    assertIterableEquals(List.of(0, 0), getZScore(handlers));
    assertEquals(0.580427, getPValue(handlers), DELTA);
  }

  @Test
  void WikiValues() {
    AggregateResultWriter arw = getWriter(new WikiValues());

    HashMap<String, BenfordResultCalculator> calculators = arw.getCalculators();
    List<ResultHandler> handlers = calculators.get(FILE_NAME).getResultHandlers();

    assertIterableEquals(List.of(1, 0), getZScore(handlers));
    assertEquals(0.152413, getPValue(handlers), DELTA);
  }

  private double getPValue(List<ResultHandler> handlers) {
    return handlers.get(1).getAggregateValues().get(0).doubleValue();
  }

  private List<Number> getZScore(List<ResultHandler> handlers) {
    return handlers.get(0).getAggregateValues();
  }
}