package com.stefan.test;

import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvException;

import java.util.ArrayList;
import java.util.List;

public class ErrorHandler implements CsvExceptionHandler {

    private final List<CsvException> exceptions;

    public ErrorHandler() {
        exceptions = new ArrayList<>();
    }

    @Override
    public CsvException handleException(CsvException e) {
        exceptions.add(e);

        System.out.printf("Exception occurred: %s - %s\n", e.getLineNumber(), e.getMessage());

        return e;
    }

    public List<CsvException> getExceptions() {
        return exceptions;
    }

}
