package com.fatih.veresiye;

import com.fatih.veresiye.entity.CurrentUser;
import com.fatih.veresiye.ui.HomeScreen;
import com.fatih.veresiye.ui.LoginApp;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //setSizeFull();
        VerticalLayout loginlayout = new VerticalLayout();
        VerticalLayout loginform = new LoginApp();
        loginlayout.setSizeFull();
        loginlayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        loginlayout.addComponent(loginform);

        if (CurrentUser.isLoggedIn()) {
            setContent(new HomeScreen());
        } else {
//            setContent(new LoginApp((UI)this));
            setContent(loginlayout);
        }
    }
    public UI getUI(){
        return (UI)this;
    }
    

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
