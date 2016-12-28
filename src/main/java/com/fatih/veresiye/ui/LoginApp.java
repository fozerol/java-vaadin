/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui;
import com.fatih.veresiye.entity.LoginService;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author fatih
 */
public class LoginApp extends VerticalLayout {

    private LoginService loginService = new LoginService();
    private TextField username;
    private PasswordField password;
    public LoginApp() {
        setWidth("400px");
        setSpacing(true);
        setMargin(true);
        addCaption();
        addForm();
        addButtons();
    }

    private void addCaption() {
        Label caption = new Label("Login to system");
        addComponent(caption);
    }

    private void addForm() {
        FormLayout loginForm = new FormLayout();
        username = new TextField("Username");
        password = new PasswordField("Password");
        loginForm.addComponents(username, password);
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        loginForm.setSpacing(true);
        loginForm.forEach(component -> component.setWidth("100%"));
        username.focus();
    }

    private void addButtons() {
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        Button forgotButton = new Button("Forgot", click -> Notification.show("Not implemented", Notification.Type.TRAY_NOTIFICATION));
        Button loginButton = new Button("Login", click -> login());
        buttonsLayout.addComponents(forgotButton, loginButton);
        addComponent(buttonsLayout);

        buttonsLayout.setSpacing(true);

        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        setComponentAlignment(buttonsLayout, Alignment.BOTTOM_RIGHT);
    }

    private void login() {
        loginService.setToken(username.getValue(),password.getValue());
        if (loginService.login()) {
            Notification.show("success");
            getUI().setContent(new HomeScreen());
        }
        else {
            Notification.show("fail");
            username.focus();
        }
    }
}
