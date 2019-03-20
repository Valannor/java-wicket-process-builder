package com.lab;

import com.lab.service.Operation;

import java.util.List;

import static com.lab.service.Calculation.calculate;

public class Test {
    public static void main(String[] args) {
        List<String> list = calculate(Operation.ADDITION,
                "1", "2");
        System.out.println(list);
    }
}
