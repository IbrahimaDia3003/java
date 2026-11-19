package com.ism.core.config.router.impl;

import java.io.IOException;

import com.ism.core.config.router.IRouter;
import com.ism.core.helper.Tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Router implements IRouter {


    public Router() {
    }

    @FXML
    @Override
    public void navigate(ActionEvent e) 
    {           
        try 
        {
            Tools.load(e, "Gestion de Dette", "/com/ism/views/gestion.store.dette.fxml");
        } 
        catch (IOException e1)
        {
            e1.printStackTrace();
         }
             
    }
}
