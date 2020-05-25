package integration.load;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.BenfordSeries;
import org.benford.factory.BenfordSeriesFactory;
import org.benford.reader.FileLoader;
import org.benford.score.ResultHandler;
import org.benford.score.ZScoreCalculator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static org.benford.BenfordConst.FIRST_DIGIT_DISTRIBUTION;
import static org.benford.factory.BenfordSeriesFactory.validateFile;

public class LoadTest {

  private static final String WID_FILES = "stats/WID";

  @Test
  void createBenfordDistribution() throws IOException, CsvValidationException {
    HashMap<String, ResultHandler> scores = getZScores(WID_FILES, 1, 4);
    scores.forEach((name, zScore) -> System.out.println(name + " " +
            zScore.getAggregateValues().get(0) + " - " +
            zScore.getAggregateValues().get(1)));
  }

  private static HashMap<String, ResultHandler> getZScores(String path, int skipLine, int column)
          throws IOException, CsvValidationException {
    ClassLoader classLoader = BenfordSeriesFactory.class.getClassLoader();
    URL url = classLoader.getResource(path);
    File[] files = new File(url.getFile()).listFiles();
    HashMap<String, ResultHandler> readers = new HashMap<>();
    for (File file : files) {
      if (validateFile(file)) {
        FileReader reader = new FileReader(file);
        FileLoader loader = new FileLoader(reader, skipLine, column);
        BenfordSeries benfordSeries = loader.createBenfordSeries();
        ZScoreCalculator calculator = new ZScoreCalculator(benfordSeries);
        readers.put(file.getName(), calculator.calculateResult(FIRST_DIGIT_DISTRIBUTION));
      }
    }
    return readers;
  }

}
