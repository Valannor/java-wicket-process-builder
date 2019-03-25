package com.lab.controller;

import org.apache.wicket.markup.html.WebPage;

public class MainPage extends WebPage {

    public MainPage() {

        add(new CalculationPanel("calculationPanel1"));
        add(new CalculationPanel("calculationPanel2"));

    }

}
