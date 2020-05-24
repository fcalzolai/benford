package org.benford.loader;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordConst;
import org.benford.BenfordSeries;
import org.benford.score.ScoreHandler;
import org.benford.score.ZScoreCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.benford.factory.BenfordSeriesFactory.getBenfordSeries;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLoaderTest {

  private static final String WID_IT_PARTIAL = "stats/WID_Data_19052020-184437.csv";
  private static final String WID_IT_ALL = "stats/WID_data_IT.csv";
  private static final String FIBONACCI = "stats/fibonacci_300.csv";
  private static final double[] EXPECTED_FIBONACCI_SERIES = new double[]{
          0,
          96,
          55,
          40,
          28,
          26,
          20,
          18,
          17,
          14
  };
  private static final double[] WID_IT_ZSCORE_EXPECTED = new double[]{
          Double.NaN,
          6.182953578,
          19.31184951,
          14.34452109,
          5.652760609,
          23.04741703,
          20.87435699,
          14.3563729,
          8.905951786,
          16.73383784,
  };

  private static final double DELTA = 0.000001;

  @Test
  void createBenfordDistribution() throws IOException, CsvValidationException {
    ScoreHandler score = getZScore(WID_IT_PARTIAL, 5, 2);
    System.out.println(score);
  }

  @Test
  void createBenfordDistribution2() throws IOException, CsvValidationException {
    ScoreHandler score = getZScore(WID_IT_ALL, 1, 4);
    Assertions.assertArrayEquals(WID_IT_ZSCORE_EXPECTED, score.getSeries(), DELTA);
  }

  @Test
  void createFibonacciBenfordDistribution() throws IOException, CsvValidationException {
    BenfordSeries benfordSeries = getBenfordSeries(FIBONACCI, 0, 0);
    ZScoreCalculator calculator = new ZScoreCalculator(benfordSeries);
    ScoreHandler scoreHandler = calculator.getScoreHandler(BenfordConst.FIRST_DIGIT_DISTRIBUTION);
    Assertions.assertArrayEquals(EXPECTED_FIBONACCI_SERIES, benfordSeries.getSeries(), 0.0);
    assertEquals(0, scoreHandler.valueNotBenfordDistributedIn95());
    assertEquals(0, scoreHandler.valueNotBenfordDistributedIn99());
  }

  private static ScoreHandler getZScore(String path, int skipLine, int column) throws IOException, CsvValidationException {
    BenfordSeries benfordSeries = getBenfordSeries(path, skipLine, column);
    ZScoreCalculator calculator = new ZScoreCalculator(benfordSeries);
    return calculator.getScoreHandler(BenfordConst.FIRST_DIGIT_DISTRIBUTION);
  }

}