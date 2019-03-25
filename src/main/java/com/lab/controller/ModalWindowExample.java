package com.lab.controller;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;

public class ModalWindowExample extends WebPage {

    private ModalWindow modalWindow;

    public ModalWindowExample() {
        modalWindow = new ModalWindow("modalWindow");

        modalWindow.setPageCreator((ModalWindow.PageCreator) MainPage::new);

        modalWindow.setTitle("Modal Window Exaple");

        modalWindow.setWindowClosedCallback((ModalWindow.WindowClosedCallback) ajaxRequestTarget -> System.out.println("Modal window is closed"));

        add(new AjaxLink<String>("modalLink") {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                modalWindow.show(ajaxRequestTarget);
            }
        });

        add(modalWindow);
    }
}
