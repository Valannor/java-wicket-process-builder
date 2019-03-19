package com.practice;

import com.practice.service.Operation;

import java.util.List;

import static com.practice.service.Calculation.calculate;

public class Test {
    public static void main(String[] args) {
        List<String> list = calculate(Operation.ADDITION,
                "1", "2");
        System.out.println(list);
    }
}
