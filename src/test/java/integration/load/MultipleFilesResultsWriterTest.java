package integration.load;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.writer.MultipleFilesResultsWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MultipleFilesResultsWriterTest {

  private static final String INPUT_WID_FILES = "stats/WID";
  private static final String OUTPUT_WID_FILES = "output";

  @Test
  void createBenfordDistribution() throws IOException, CsvValidationException {
    MultipleFilesResultsWriter wdp = new MultipleFilesResultsWriter(INPUT_WID_FILES, OUTPUT_WID_FILES);
    wdp.computeData();
  }

}
