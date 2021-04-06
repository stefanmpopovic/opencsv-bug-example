package com.stefan.test;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class ExampleTest {

    private CsvToBean<TestEntity> csvToBean;

    private ErrorHandler errorHandler;

    @Before
    public void setUp() throws Exception {
        Reader reader = new FileReader("samples/testFile.csv");

        errorHandler = new ErrorHandler();

        csvToBean = new CsvToBeanBuilder<TestEntity>(reader)
                .withType(TestEntity.class)
                .withThrowExceptions(false)
                .withExceptionHandler(errorHandler)
                .build();
    }

    @After
    public void tearDown() {
        System.out.println("Exceptions:");

        for (CsvException ex : errorHandler.getExceptions()) {
            System.out.printf("%s - %s\n", ex.getLineNumber(), ex.getMessage());
        }
    }

    @Test
    public void testUsingParse() { // This passes
        List<TestEntity> entities = csvToBean.parse();

        entities.forEach(entity -> System.out.printf("Entity: %s\n", entity));
    }

    @Test
    public void testUsingIterator() { // This hangs
        System.out.println("Creating iterator");

        Iterator<TestEntity> iterator = csvToBean.iterator();

        System.out.println("Starting iteration");

        while(iterator.hasNext()) {
            System.out.println("Getting next entity...");

            TestEntity next = iterator.next();

            System.out.printf("Entity: %s\n", next);
        }
    }

}
