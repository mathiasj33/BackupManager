package org.bitbucket.mathiasj33;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author Mathias
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ListView listView;
    @FXML
    private TextField storageField;
    
    private Backup backup;
    
    public FXMLDocumentController() {
        backup = new Backup();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setPlaceholder(new Label("Begin adding new files and folders by clicking on 'Add'"));
        listView.setItems(backup.getBackupInfos());
        storageField.textProperty().bind(backup.getStorageDirectory());
    } 
    
    @FXML
    private void addFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        File file = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        if(file == null) return;
        backup.addBackupInfo(new FileBackupInfo(file.getAbsolutePath()));
    }
    
    @FXML
    private void chooseStorage(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Choose a storage folder");
        File dir = dc.showDialog(anchorPane.getScene().getWindow());
        if(dir == null) return;
        backup.setStorageDirectory(dir.getAbsolutePath());
    }
}
