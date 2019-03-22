package com.lab.model;

public class OperationUnit {
    private String operand1 = "0";
    private String operand2 = "0";

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    @Override
    public String toString() {
        return "OperationUnit{" +
                "operand1='" + operand1 + '\'' +
                ", operand2='" + operand2 + '\'' +
                '}';
    }
}
