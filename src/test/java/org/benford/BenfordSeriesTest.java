package org.benford;

import org.benford.data.AimcValues;
import org.benford.data.WikiPopulationValues;
import org.benford.data.WikiValues;
import org.benford.zscore.ZScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BenfordSeriesTest {

  private static final AimcValues AIMC = new AimcValues();
  private static final WikiValues WIKI_RIVER = new WikiValues();
  private static final WikiPopulationValues WIKI_POPULATION = new WikiPopulationValues();

  private final BenfordSeries aimc;
  private final BenfordSeries wiki_river;
  private final BenfordSeries wiki_population;

  public BenfordSeriesTest() {
    aimc = new BenfordSeries(AIMC.getValues());
    wiki_river = new BenfordSeries(WIKI_RIVER.getValues());
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
  void WikiRiverDigitDistribution() {
    double[] digitDistribution = wiki_river.getDigitDistribution();
    Assertions.assertArrayEquals(WIKI_RIVER.getExpectedDigitDistribution(), digitDistribution, WIKI_RIVER.getPercentageDelta());
  }

  @Test
  void WikiRiverZScoreDistribution() {
    ZScore zScore = wiki_river.getZscoreFirstDigit();
    assertEquals(1, zScore.valueNotBenfordDistributedIn95());
    assertEquals(0, zScore.valueNotBenfordDistributedIn99());
  }

  @Test
  void WikiPopulationDigitDistribution() {
    double[] digitDistribution = wiki_population.getDigitDistribution();
    Assertions.assertArrayEquals(WIKI_POPULATION.getExpectedDigitDistribution(), digitDistribution, WIKI_POPULATION.getPercentageDelta());
    System.out.println(Arrays.toString(digitDistribution));
  }

  @Test
  void WikiPopulationZScoreDistribution() {
    ZScore zScore = wiki_population.getZscoreFirstDigit();
    assertEquals(0, zScore.valueNotBenfordDistributedIn95());
    assertEquals(0, zScore.valueNotBenfordDistributedIn99());
  }
}