package com.github.binarylei.validation;

import org.apache.commons.validator.routines.IntegerValidator;
import org.junit.Assert;
import org.junit.Test;

public class ApacheValidatorMain {

    @Test
    public void IntegerValidatorTest() {
        IntegerValidator integerValidator = new IntegerValidator();
        Assert.assertTrue(integerValidator.maxValue(1, 10));
    }

}
