package org.bitbucket.mathiasj33.backupManager;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLFilePropertiesController implements Initializable {
    @FXML
    private Label header;
    @FXML
    private TextField targetSubFolderField;
    
    private Backup backup;
    private FileBackupInfo info;
    private Stage stage;

    public FXMLFilePropertiesController(Backup backup, FileBackupInfo info, Stage stage) {
        this.backup = backup;
        this.info = info;
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        header.setText(header.getText() + "\n" + new File(info.getPath()).getName());
        targetSubFolderField.textProperty().bindBidirectional(info.getRelativeFolder());
        Platform.runLater(header::requestFocus);
    }

    @FXML
    private void close() {
        stage.close();
    }
}
