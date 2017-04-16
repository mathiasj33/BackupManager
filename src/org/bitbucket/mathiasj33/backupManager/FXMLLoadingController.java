/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.mathiasj33.backupManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Mathias
 */
public class FXMLLoadingController implements Initializable, Callback {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;
    @FXML
    private Button closeButton;

    private Stage stage;
    private boolean finished;

    public FXMLLoadingController(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(e -> tryClose(e));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void tryClose(WindowEvent e) {
        if (finished)
            close();
        else
            e.consume();
    }

    @FXML
    private void close() {
        stage.close();
    }

    @Override
    public void progressUpdated(int commandsExecuted, int totalCommands) {
        Platform.runLater(() -> 
        {
            progressLabel.setText(commandsExecuted + "/" + totalCommands);
            progressBar.setProgress(((float) commandsExecuted) / totalCommands);
            if (commandsExecuted == totalCommands) {
                finished = true;
                closeButton.setDisable(false);
            }
        });
    }
}
