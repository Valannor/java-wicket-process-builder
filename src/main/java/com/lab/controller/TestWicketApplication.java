package com.lab.controller;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class TestWicketApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return MainPage.class;
    }

    public void init() {
        super.init();
    }

}
