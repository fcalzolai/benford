package org.benford;

import org.benford.constraints.Percentage;

import javax.validation.constraints.Size;

public class DigitDistribution extends DigitSerie {

  public DigitDistribution(@Size(min = 10, max = 10) @Percentage Double[] serie) {
    super(serie);
  }
}

