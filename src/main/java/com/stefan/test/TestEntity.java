package com.stefan.test;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.validators.PreAssignmentValidator;

public class TestEntity {

    @CsvBindByName(column = "id", required = true)
    @PreAssignmentValidator(validator = TestValidator.class)
    private String id;

    @CsvBindByName(column = "name", required = true)
    private String name;

    @CsvBindByName(column = "status", required = true)
    private String status;

    public TestEntity() {
    }

    public TestEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("TestEntity[id = %s, name = %s]", id, name);
    }
}
