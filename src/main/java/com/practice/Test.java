package com.practice;

import java.util.List;

import static com.practice.model.ScriptExecutor.execute;

public class Test {
    public static void main(String[] args) {
        List<String> list = execute("cmd.exe", "/c",
                "src\\main\\resources\\Division.groovy", "1", "2");
        System.out.println(list);
    }
}
