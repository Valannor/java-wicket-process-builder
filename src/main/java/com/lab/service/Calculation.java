package com.lab.service;

import java.util.List;

import static com.lab.model.ScriptExecutor.execute;

public interface Calculation {
    static List<String> calculate(Operation operation, String operand1, String operand2) {
        return execute("cmd.exe", "/c", operation.getScript(), operand1, operand2);
    }
}
