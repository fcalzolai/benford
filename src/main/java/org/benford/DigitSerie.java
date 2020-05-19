package org.benford;

import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class DigitSerie {

  Double[] serie;

  public DigitSerie(@Size(min = 10, max = 10) Double[] serie) {
    this.serie = serie;
  }

  public DigitSerie() {
    serie = new Double[10];
  }
}
