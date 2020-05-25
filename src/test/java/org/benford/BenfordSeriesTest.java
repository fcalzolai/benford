package org.benford;

import org.benford.data.AimcValues;
import org.benford.data.MunicipalityValues;
import org.benford.data.WikiPopulationValues;
import org.benford.data.WikiValues;
import org.benford.score.ResultHandler;
import org.benford.score.ZScoreCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.benford.BenfordConst.FIRST_DIGIT_DISTRIBUTION;
import static org.benford.BenfordConst.SECOND_DIGIT_DISTRIBUTION;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenfordSeriesTest {

  private static final AimcValues AIMC = new AimcValues();
  private static final WikiValues WIKI_RIVER = new WikiValues();
  private static final WikiPopulationValues WIKI_POPULATION = new WikiPopulationValues();
  private static final MunicipalityValues MUNICIPALITY = new MunicipalityValues();

  private final BenfordSeries aimc;
  private final BenfordSeries wiki_river;
  private final BenfordSeries wiki_population;
  private final BenfordSeries municipality;

  public BenfordSeriesTest() {
    aimc = new BenfordSeries(AIMC.getValues());
    wiki_river = new BenfordSeries(WIKI_RIVER.getValues());
    wiki_population = new BenfordSeries(WIKI_POPULATION.getValues());
    municipality = new BenfordSeries(MUNICIPALITY.getValues());
  }

  @Test
  void aimcDigitDistribution() {
    double[] digitDistribution = aimc.getDigitDistribution();
    Assertions.assertArrayEquals(AIMC.getExpectedDigitDistribution(), digitDistribution, AIMC.getPercentageDelta());
  }

  @Test
  void aimcCount() {
    assertEquals(2051, aimc.getCount());
  }

  @Test
  void aiimcZscoreSecondDigit() {
    ZScoreCalculator calculator = new ZScoreCalculator(aimc);
    ResultHandler resultHandler = calculator.calculateResult(SECOND_DIGIT_DISTRIBUTION);
    Assertions.assertArrayEquals(AIMC.getExpectedZScore(), resultHandler.getSeries(), AIMC.getZScoreDelta());
  }

  @Test
  void wikiRiverDigitDistribution() {
    double[] digitDistribution = wiki_river.getDigitDistribution();
    Assertions.assertArrayEquals(WIKI_RIVER.getExpectedDigitDistribution(), digitDistribution, WIKI_RIVER.getPercentageDelta());
  }

  @Test
  void wikiRiverZScoreDistribution() {
    ZScoreCalculator calculator = new ZScoreCalculator(wiki_river);
    ResultHandler zScore = calculator.calculateResult(FIRST_DIGIT_DISTRIBUTION);
    List<Number> values = zScore.getAggregateValues();
    assertEquals(1, values.get(0));
    assertEquals(0, values.get(1));
  }

  @Test
  void wikiPopulationDigitDistribution() {
    double[] digitDistribution = wiki_population.getDigitDistribution();
    Assertions.assertArrayEquals(WIKI_POPULATION.getExpectedDigitDistribution(), digitDistribution, WIKI_POPULATION.getPercentageDelta());
  }

  @Test
  void wikiPopulationZScoreDistribution() {
    ZScoreCalculator calculator = new ZScoreCalculator(wiki_population);
    ResultHandler zScore = calculator.calculateResult(FIRST_DIGIT_DISTRIBUTION);
    List<Number> values = zScore.getAggregateValues();
    assertEquals(0, values.get(0));
    assertEquals(0, values.get(1));
  }

  @Test
  void municipalityDigitDistribution() {
    double[] digitDistribution = municipality.getDigitDistribution();
    Assertions.assertArrayEquals(MUNICIPALITY.getExpectedDigitDistribution(), digitDistribution, MUNICIPALITY.getPercentageDelta());
  }

  @Test
  void WikiPopulationZScoreDistribution() {
    ZScoreCalculator calculator = new ZScoreCalculator(municipality);
    ResultHandler zScore = calculator.calculateResult(FIRST_DIGIT_DISTRIBUTION);
    List<Number> values = zScore.getAggregateValues();
    assertEquals(2, values.get(0));
    assertEquals(1, values.get(1));
  }
}