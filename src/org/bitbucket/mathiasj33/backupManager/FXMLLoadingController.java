/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.mathiasj33.backupManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Mathias
 */
public class FXMLLoadingController implements Initializable {

    @FXML
    private TextArea console;
    @FXML
    private ProgressBar progressBar;
    
    private BufferedReader reader;

    public FXMLLoadingController(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        final String copy = line;
                        Platform.runLater(() -> console.appendText(copy + "\n"));
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FXMLLoadingController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        new Thread(r).start();
    }
}
