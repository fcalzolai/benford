package org.benford.constraints;

import org.benford.Consts;
import org.benford.DigitDistribution;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PercentageValidator implements ConstraintValidator<Percentage, DigitDistribution> {

  @Override
  public void initialize(Percentage constraintAnnotation) {

  }

  @Override
  public boolean isValid(DigitDistribution value, ConstraintValidatorContext context) {
    Double[] serie = value.getSerie();

    Double actual = 0.0;
    for (Double d : serie) {
      actual += d;
    }
    return Math.abs(actual - 1.0) < Consts.DELTA;
  }
}
