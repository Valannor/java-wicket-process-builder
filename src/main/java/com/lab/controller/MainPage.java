package com.lab.controller;

import com.lab.model.OperationUnit;
import com.lab.service.Calculation;
import com.lab.service.Operation;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.util.Arrays;

public class MainPage extends WebPage {

    private Form calculationForm;

    private Label operandsLabel;
    private TextField<String> operandField1;
    private TextField<String> operandField2;

    private Label operationsLabel;
    private DropDownChoice<Operation> operationsChoice;

    private Button submit;

    private Label resultLabel;
    private Label resultValue;

    private OperationUnit operationUnit;

    public MainPage() {

        init();

        add(calculationForm);

        calculationForm.add(operandsLabel);
        calculationForm.add(operandField1);
        calculationForm.add(operandField2);

        calculationForm.add(operationsLabel);
        calculationForm.add(operationsChoice);

        calculationForm.add(resultLabel);
        calculationForm.add(resultValue);

        calculationForm.add(submit);
    }

    private void init() {
        operationUnit = new OperationUnit();

        calculationForm = new Form("calculationForm");

        operandsLabel = new Label("label_operands", (IModel) () -> getString("label_operands"));

        operandField1 = new TextField<>("operandField1", new PropertyModel<String>(operationUnit, "operand1"));
        operandField2 = new TextField<>("operandField2", new PropertyModel<String>(operationUnit, "operand2"));

        operationsLabel = new Label("label_operations", (IModel) () -> getString("label_operations"));
        operationsChoice = new DropDownChoice<>("operations", new Model<>(), Arrays.asList(Operation.values()));

        resultLabel = new Label("label_result", (IModel) () -> getString("label_result"));
        resultValue = new Label("lbl_4", new Model<>(""));
        resultValue.setOutputMarkupId(true);

        submit = new AjaxButton("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                String operand1 = operandField1.getInput();
                String operand2 = operandField2.getInput();
                String calculation = Calculation.calculate(operationsChoice.getConvertedInput(), operand1, operand2).get(0);
                resultValue.setDefaultModel(new Model<>(calculation));
                target.add(resultValue);
            }
        };
    }
}
