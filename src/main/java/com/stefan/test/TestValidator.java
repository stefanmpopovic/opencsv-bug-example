package com.stefan.test;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.validators.StringValidator;
import com.opencsv.exceptions.CsvValidationException;

import static java.lang.String.format;

public class TestValidator implements StringValidator {

    @Override
    public boolean isValid(String value) {
        return value.length() < 2;
    }

    @Override
    public void validate(String value, BeanField field) throws CsvValidationException {
        String fieldName = field.getField().getName();

        if (!isValid(value)) {
            throw new CsvValidationException(format("Invalid value '%s' for field '%s'", value, fieldName));
        }
    }

    @Override
    public void setParameterString(String value) {

    }
}
