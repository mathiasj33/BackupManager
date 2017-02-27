package org.bitbucket.mathiasj33.backupManager;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    
    private List<PropertiesAppliedListener> listeners = new ArrayList<>();

    public FXMLFilePropertiesController(Backup backup, FileBackupInfo info, Stage stage) {
        this.backup = backup;
        this.info = info;
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        header.setText(header.getText() + "\n" + new File(info.getPath()).getName());
        targetSubFolderField.textProperty().bindBidirectional(info.getRelativeFolder());
        
        stage.setOnCloseRequest(e -> apply());
        Platform.runLater(header::requestFocus);
    }

    public void addPropertiesAppliedListener(PropertiesAppliedListener listener) {
        listeners.add(listener);
    }
    
    @FXML
    private void close() {
        apply();
        stage.close();
    }
    
    private void apply() {
        listeners.forEach(l -> l.propertiesApplied());
    }
}
