package org.benford2;

import org.benford2.data.AimcValues;
import org.benford2.data.WikiPopulationValues;
import org.benford2.data.WikiValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenfordSeriesTest {

  private static final AimcValues AIMC = new AimcValues();
  private static final WikiValues WIKI = new WikiValues();
  private static final WikiPopulationValues WIKI_POPULATION = new WikiPopulationValues();

  private final BenfordSeries aimc;
  private final BenfordSeries wiki;
  private final BenfordSeries wiki_population;

  public BenfordSeriesTest() {
    aimc = new BenfordSeries(AIMC.getValues());
    wiki = new BenfordSeries(WIKI.getValues());
    wiki_population = new BenfordSeries(WIKI_POPULATION.getValues());
  }

  @Test
  void AimcDigitDistribution() {
    double[] digitDistribution = aimc.getDigitDistribution();
    Assertions.assertArrayEquals(AIMC.getExpectedDigitDistribution(), digitDistribution, AIMC.getPercentageDelta());
  }

  @Test
  void AimcCount() {
    int count = aimc.getCount();
    assertEquals(2051, count);
  }

  @Test
  void AiimcZscoreSecondDigit() {
    ZScore digitDistribution = aimc.getZscoreSecondDigit();
    Assertions.assertArrayEquals(AIMC.getExpectedZScore(), digitDistribution.series, AIMC.getZScoreDelta());
  }

  @Test
  void WikiDigitDistribution() {
    double[] digitDistribution = wiki.getDigitDistribution();
    Assertions.assertArrayEquals(WIKI.getExpectedDigitDistribution(), digitDistribution, WIKI.getPercentageDelta());
  }

  @Test
  void WikiPopulationDigitDistribution() {
    double[] digitDistribution = wiki_population.getDigitDistribution();
    Assertions.assertArrayEquals(WIKI_POPULATION.getExpectedDigitDistribution(), digitDistribution, WIKI_POPULATION.getPercentageDelta());
    System.out.println(Arrays.toString(digitDistribution));
    System.out.println(wiki_population.getZscoreFirstDigit());
  }
}