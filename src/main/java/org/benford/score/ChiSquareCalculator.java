package org.benford.score;

public class ChiSquareCalculator {

  private final double[] digitDistributioin;
  private final int count;

  public ChiSquareCalculator(int count, double[] digitDistributioin) {
    this.count = count;
    this.digitDistributioin = digitDistributioin;
  }

  public double[] calculateChiSquare(double[] expected) {
    double[] chiSquare = new double[10];

    for (int i = 0; i < digitDistributioin.length; i++) {
      chiSquare[i] = calculateChiSquare(digitDistributioin[i], expected[i]);
    }

    return chiSquare;
  }

  public Double calculateChiSquare(Double actual, Double expected) {
    double actualVal = actual * count;
    double expectedVal = expected * count;
    double numerator = Math.pow((actualVal - expectedVal), 2.0);
    return numerator / expectedVal;
  }
}