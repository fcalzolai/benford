package org.benford;

import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class BenfordSerie {

  private Double[] serie;

  public BenfordSerie(@Size(min = 10, max = 10) Double[] serie) {
    this.serie = serie;
  }

  public BenfordSerie() {
    serie = new Double[10];
  }
}
