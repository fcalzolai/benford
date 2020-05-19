package org.benford;

public class BenfordSeries extends DigitSeries {

  private int count;
  private DigitDistribution digitDistribution;

  public BenfordSeries(int count) {
    super();
    this.count = count;
  }

  public BenfordSeries(double[] serie, int count) {
    super(serie);
    this.count = count;
  }
}
