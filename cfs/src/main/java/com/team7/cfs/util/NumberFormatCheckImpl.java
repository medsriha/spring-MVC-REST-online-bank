package com.team7.cfs.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberFormatCheckImpl implements ConstraintValidator<NumberFormatCheck, Object> {
    @Override
    public void initialize(NumberFormatCheck nameFormatCheck) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String input = o.toString();
        try {
            double amount = Double.parseDouble(input);
            if (amount < 0) {
                return false;
            }
//            if (input.indexOf(".") >= 0 && input.substring(input.indexOf(".")).length() > 3) {
//                return false;
//            }
            if (amount * 100 % 1 != 0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
