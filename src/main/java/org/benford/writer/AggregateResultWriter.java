package org.benford.writer;

import lombok.Getter;
import org.benford.score.BenfordResultCalculator;
import org.benford.score.ResultHandler;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AggregateResultWriter {

  private static final String COLUMN_NAMES = "Source file, " +
          "Country, " +
          "# Lines, " ;

  private static final String SEP = ",";

  private final HashMap<String, BenfordResultCalculator> calculators;

  public AggregateResultWriter(HashMap<String, BenfordResultCalculator> calculators) {
    this.calculators = calculators;
  }

  public String toCsv() {
    StringBuilder builder = new StringBuilder();
    appendColumnsNames(builder);
    appendValues(builder);
    return builder.toString();
  }

  private void appendValues(StringBuilder builder) {
    calculators.forEach((file, brc) -> {
      builder.append(file).append(SEP);
      builder.append(getCountry(file)).append(SEP);
      builder.append(brc.getBenfordSeries().getCount()).append(SEP);
      builder.append(getValues(brc));
      builder.append("\n");
    });
  }

  private String getValues(BenfordResultCalculator brc) {
    return brc.getResultHandlers().stream()
            .map(ResultHandler::getAggregateValues)
            .flatMap(List::stream)
            .map(Number::toString)
            .collect(Collectors.joining(SEP));
  }

  private void appendColumnsNames(StringBuilder builder) {
    builder.append("Source file,Country,# Lines," );

    BenfordResultCalculator brc = calculators.values().iterator().next();
    builder.append(brc.getAggregateColumnsNames(SEP));
    builder.append("\n");
  }

  private String getCountry(String file) {
    return CountryCalculator.getCountry(file);
  }

}
