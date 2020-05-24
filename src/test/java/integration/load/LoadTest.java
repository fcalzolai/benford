package integration.load;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.score.ZScoreResult;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.benford.factory.BenfordSeriesFactory.getZScores;

public class LoadTest {

  private static final String WID_FILES = "stats/WID";

  @Test
  void createBenfordDistribution() throws IOException, CsvValidationException {
    HashMap<String, ZScoreResult> scores = getZScores(WID_FILES, 1, 4);
    scores.forEach((name, zScore) -> System.out.println(name + " " + zScore.valueNotBenfordDistributedIn95() + " - " + zScore.valueNotBenfordDistributedIn99()));
  }

}
