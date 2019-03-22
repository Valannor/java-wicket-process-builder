package com.lab.controller;

import com.lab.model.OperationUnit;
import com.lab.service.Calculation;
import com.lab.service.Operation;
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

    public MainPage() {
        add(new CalculationForm("calculationForm"));
    }

    public class CalculationForm extends Form<String> {

        private TextField<String> operandField1;
        private TextField<String> operandField2;

        private DropDownChoice<Operation> operations;
        private Button submit;

        private Label resultField;

        private OperationUnit operationUnit;

        // TODO: 21.03.2019 Extract AbstractReadOnlyModel for less code
        public CalculationForm(String id) {
            super(id);

            operationUnit = new OperationUnit();

            add(new Label("label_operands", (IModel) () -> getString("label_operands")));
            add(this.operandField1 = new TextField<>("operandField1", new PropertyModel(operationUnit, "operand1")));
            add(this.operandField2 = new TextField<>("operandField2", new PropertyModel(operationUnit, "operand2")));

            add(new Label("label_operations", (IModel) () -> getString("label_operations")));

            // TODO: Normal initialization with creation of basic model
            add(this.operations = new DropDownChoice<>("operations", new Model<>(), Arrays.asList(Operation.values())));
            add(this.submit = new Button("submit"));

            // TODO: This is the normal Label initialization for java 1.7
//            add(new Label("label_result", new AbstractReadOnlyModel<String>() {
//                @Override
//                public String getObject() {
//                    return getString("label_result");
//                }
//            }));

            // TODO: This is how it looks in java 1.8 (lambda)
            add(new Label("label_result", (IModel) () -> getString("label_result")));

            add(this.resultField = new Label("lbl_4", new PropertyModel(operationUnit, "result")));

        }

        @Override
        public void onSubmit() {
            String operand1 = operandField1.getInput();
            String operand2 = operandField2.getInput();
            String calculation = Calculation.calculate(operations.getConvertedInput(), operand1, operand2).get(0);
            resultField.setDefaultModel(new Model<>(calculation));

            // TODO: Here we check if value really have changed
            System.out.println(operationUnit);
        }
    }
}
