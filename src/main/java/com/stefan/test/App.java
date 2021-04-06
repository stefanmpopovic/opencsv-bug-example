package com.stefan.test;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class App
{

    public static void main( String[] args ) throws Exception
    {
        Reader reader = new FileReader("samples/testFile.csv");

        ErrorHandler errorHandler = new ErrorHandler();

        CsvToBean<TestEntity> csvToBean = new CsvToBeanBuilder<TestEntity>(reader)
                .withType(TestEntity.class)
                .withThrowExceptions(false)
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withExceptionHandler(errorHandler)
                .build();

        usingIterator(csvToBean);
//        usingParse(csvToBean);

        System.out.println("Exceptions:");

        for (CsvException ex : errorHandler.getExceptions()) {
            System.out.printf("%s - %s\n", ex.getLineNumber(), ex.getMessage());
        }
    }

    private static void usingIterator(CsvToBean<TestEntity> csvToBean) { // This hangs
        System.out.println("Creating iterator");

        Iterator<TestEntity> iterator = csvToBean.iterator();

        System.out.println("Starting iteration");

        while(iterator.hasNext()) {
            System.out.println("Getting next entity...");

            TestEntity next = iterator.next();

            System.out.printf("Entity: %s\n", next);
        }
    }

    private static void usingParse(CsvToBean<TestEntity> csvToBean) { // This passes
        List<TestEntity> entities = csvToBean.parse();

        entities.forEach(entity -> System.out.printf("Entity: %s\n", entity));
    }
}
