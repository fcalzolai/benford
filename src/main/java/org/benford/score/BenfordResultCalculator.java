package org.benford.score;

import lombok.Getter;
import org.benford.BenfordSeries;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.benford.BenfordConst.FIRST_DIGIT_DISTRIBUTION;

@Getter
public class BenfordResultCalculator {

  private final BenfordSeries benfordSeries;
  private final List<Calculator> calculators;
  private List<ResultHandler> resultHandlers;

  public BenfordResultCalculator(BenfordSeries benfordSeries) {
    this.benfordSeries = benfordSeries;
    calculators = getCalculator(benfordSeries);
  }

  public List<ResultHandler> getResultHandlers() {
    if (resultHandlers == null) {
      resultHandlers = calculators.stream()
              .map(c -> c.calculateResult(FIRST_DIGIT_DISTRIBUTION))
              .collect(Collectors.toList());
    }

    return resultHandlers;
  }

  private List<Calculator> getCalculator(BenfordSeries benfordSeries) {
    List<Calculator> calculators = new LinkedList<>();
    calculators.add(new ZScoreCalculator(benfordSeries));
    calculators.add(new ChiSquareCalculator(benfordSeries));
    return calculators;
  }

  public String getColumnsNames(String sep) {
    return getResultHandlers().stream()
            .map(ResultHandler::getColumnName)
            .collect(Collectors.joining(sep));
  }

  public String getAggregateColumnsNames(String sep) {
    return getResultHandlers().stream()
            .map(ResultHandler::getAggregateColumnsNames)
            .flatMap(List::stream)
            .collect(Collectors.joining(sep));
  }
}
