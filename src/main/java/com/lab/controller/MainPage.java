package com.lab.controller;

import com.lab.service.Calculation;
import com.lab.service.Operation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

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

        private Label result;

        // TODO: 21.03.2019 Extract AbstractReadOnlyModel for less code
        public CalculationForm(String id) {
            super(id);

            add(new Label("label_operands", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return getString("label_operands");
                }
            }));
            add(this.operandField1 = new TextField<>("operandField1", new Model<>()));
            add(this.operandField2 = new TextField<>("operandField2", new Model<>()));

            add(new Label("label_operations", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return getString("label_operations");
                }
            }));
            add(this.operations = new DropDownChoice<>("operations", new Model<>(), Arrays.asList(Operation.values())));
            add(this.submit = new Button("submit"));

            add(new Label("label_result", new AbstractReadOnlyModel<String>() {
                @Override
                public String getObject() {
                    return getString("label_result");
                }
            }));
            add(this.result = new Label("lbl_4", new Model<>("")));
        }

        @Override
        public void onSubmit() {
            System.out.println("I'm done");

            String operand1 = operandField1.getInput();
            String operand2 = operandField2.getInput();
            String calculation = Calculation.calculate(operations.getConvertedInput(), operand1, operand2).get(0);
            result.setDefaultModel(new Model<>(calculation));
        }
    }
}
