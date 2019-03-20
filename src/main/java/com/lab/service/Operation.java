package com.lab.service;

public enum Operation {
    ADDITION("src\\main\\resources\\Addition.groovy"),
    SUBTRACTION("src\\main\\resources\\Subtraction.groovy"),
    MULTIPLICATION("src\\main\\resources\\Multiplication.groovy"),
    DIVISION("src\\main\\resources\\Division.groovy");

    private String s;

    Operation(String s) {
        this.s = s;
    }

    public String getScript() {
        return s;
    }
}
