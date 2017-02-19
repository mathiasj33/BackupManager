
package org.bitbucket.mathiasj33;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLPropertiesController implements Initializable {

    private FolderBackupInfo info;
    private Stage stage;
    
    @FXML
    private Label header;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Button okButton;
    
    public FXMLPropertiesController(FolderBackupInfo info, Stage stage) {
        this.info = info;
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        header.setText(header.getText() + "\n" + info.getPath());
        checkBox.selectedProperty().bindBidirectional(info.includeSubDirectoriesProperty());
        stage.setOnCloseRequest(e -> apply());
        
        Platform.runLater(header::requestFocus);
    }

    @FXML
    private void close() {
        apply();
        stage.close();
    }
    
    private void apply() {
    }
}
