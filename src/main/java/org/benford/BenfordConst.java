package org.benford;

public class BenfordConst {

  public static final double[] FIRST_DIGIT_DISTRIBUTION = new double[]{
          0.0,
          0.30103,
          0.176091,
          0.124939,
          0.09691,
          0.0791812,
          0.0669468,
          0.0579919,
          0.0511525,
          0.0457575
  };

  public static final double[] SECOND_DIGIT_DISTRIBUTION = new double[]{
          0.1197,
          0.1139,
          0.1088,
          0.1043,
          0.1003,
          0.0967,
          0.0934,
          0.0904,
          0.0876,
          0.0850
  };

  public static double ZSCORE_95 = 1.96;

  public static double ZSCORE_99 = 2.58;

  public static double CHI_SQUARE_95 = 15.51;

  public static double CHI_SQUARE_99 = 20.09;

}
