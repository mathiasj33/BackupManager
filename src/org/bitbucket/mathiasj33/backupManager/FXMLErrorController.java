/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.mathiasj33.backupManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mathias
 */
public class FXMLErrorController implements Initializable {

    @FXML
    private Label errorLabel;
    private String errorMsg;
    private Stage stage;
    
    public void showWindow(String errorMsg) throws IOException {
        this.errorMsg = errorMsg;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLError.fxml"));
        loader.setController(this);
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText(errorMsg);
    }    
    
    @FXML
    private void close() {
        stage.close();
    }
}
