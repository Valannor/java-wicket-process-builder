package com.lab.controller;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class ModalWindowApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return ModalWindowExample.class;
    }

    public void init() {
        super.init();
    }

}
