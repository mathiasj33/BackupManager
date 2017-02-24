package org.bitbucket.mathiasj33.backupManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Mathias
 */
public class FXMLDocumentController implements Initializable, PropertiesAppliedListener {
    
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ListView<BackupInfo> listView;
    @FXML
    private TextField storageField;
    @FXML
    private Button editButton;
    @FXML
    private Button removeButton;
    
    private Storage storage;
    private Backup backup;
    
    public FXMLDocumentController() {
        storage = new JsonStorage();
        try {
            backup = storage.retrieve();
        } catch(StoreException e) {
            backup = new Backup();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setPlaceholder(new Label("Begin adding new files and folders by clicking on 'Add'"));
        listView.setItems(backup.getBackupInfos());
        storageField.textProperty().bind(backup.getStorageDirectory());
        
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            editButton.setDisable(newValue == null || !(newValue instanceof FolderBackupInfo));
            removeButton.setDisable(newValue == null);
        });
        
        
        
        Platform.runLater(anchorPane::requestFocus);
    } 
    
    @FXML
    private void addFile(ActionEvent event) {
        File file = getFile("Choose a file");
        if(file == null) return;
        backup.addBackupInfo(new FileBackupInfo(file.getAbsolutePath()));
        storage.tryStore(backup);
    }
    
    @FXML
    private void addFolder(ActionEvent event) {
        File file = getDirectory("Choose a folder");
        if(file == null) return;
        backup.addBackupInfo(new FolderBackupInfo(file.getAbsolutePath()));
        storage.tryStore(backup);
    }
    
    @FXML
    private void chooseStorage(ActionEvent event) {
        File dir = getDirectory("Choose a storage folder");
        if(dir == null) return;
        backup.setStorageDirectory(dir.getAbsolutePath());
        storage.tryStore(backup);
    }
    
    public File getFile(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        return fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
    }
    
    public File getDirectory(String title) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle(title);
        return dc.showDialog(anchorPane.getScene().getWindow());
    }
    
    @FXML
    public void edit(ActionEvent event) throws IOException {
        FolderBackupInfo info = (FolderBackupInfo) listView.getSelectionModel().getSelectedItem();
        
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLProperties.fxml"));
        FXMLPropertiesController propertiesConroller = new FXMLPropertiesController(info, stage);
        propertiesConroller.addPropertiesAppliedListener(this);
        loader.setController(propertiesConroller);
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(info.getPath());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
    
    @Override
    public void propertiesApplied() {
        storage.tryStore(backup);
    }
    
    @FXML
    public void remove(ActionEvent event) {
        backup.removeBackupInfo(listView.getSelectionModel().getSelectedItem());
        storage.tryStore(backup);
    }
}