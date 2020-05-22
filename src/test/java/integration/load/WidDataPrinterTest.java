package integration.load;

import com.opencsv.exceptions.CsvValidationException;
import org.benford.printer.WidDataPrinter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WidDataPrinterTest {

  private static final String INPUT_WID_FILES = "stats/WID";
  private static final String OUTPUT_WID_FILES = "output";

  @Test
  void createBenfordDistribution() throws IOException, CsvValidationException {
    WidDataPrinter wdp = new WidDataPrinter(INPUT_WID_FILES, OUTPUT_WID_FILES);
    wdp.computeData();
  }

}
