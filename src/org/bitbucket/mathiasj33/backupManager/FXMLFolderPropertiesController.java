
package org.bitbucket.mathiasj33.backupManager;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLFolderPropertiesController implements Initializable {

    private Backup backup;
    private FolderBackupInfo info;
    private Stage stage;
    private List<PropertiesAppliedListener> listeners = new ArrayList<>();
    
    @FXML
    private Label header;
    @FXML
    private TextArea fileTypesTextArea;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextArea excludeFoldersTextArea;
    @FXML
    private Button addButton;
    @FXML
    private TextField targetSubFolderField;
    
    public FXMLFolderPropertiesController(Backup backup, FolderBackupInfo info, Stage stage) {
        this.backup = backup;
        this.info = info;
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        header.setText(header.getText() + "\n" + new File(info.getPath()).getName());
        checkBox.selectedProperty().bindBidirectional(info.includeSubDirectoriesProperty());
        excludeFoldersTextArea.disableProperty().bind(checkBox.selectedProperty().not());
        addButton.disableProperty().bind(checkBox.selectedProperty().not());
        
        fileTypesTextArea.setText(toCommaSeparatedString(info.getFilesToExclude()));
        excludeFoldersTextArea.setText(toCommaSeparatedString(info.getFoldersToExclude()));        
        
        targetSubFolderField.textProperty().bindBidirectional(info.getTargetSubFolder());
        
        stage.setOnCloseRequest(e -> apply());
        Platform.runLater(header::requestFocus);
    }

    public void addPropertiesAppliedListener(PropertiesAppliedListener listener) {
        listeners.add(listener);
    }
    
    @FXML
    private void addSubFolder() {
        File dir = JavaFXUtils.getDirectory("Choose a subfolder", new File(info.getPath()),stage);
        if(dir == null || excludeFoldersTextArea.getText().contains(dir.getName())) return;
        if(!excludeFoldersTextArea.getText().equals("")) excludeFoldersTextArea.appendText(", ");
        excludeFoldersTextArea.appendText(dir.getName());
    }
    
    @FXML
    private void close() {
        apply();
        stage.close();
    }
    
    private void apply() {
        String[] fileTypes = getFileTypes();
        info.setFilesToExclude(fileTypes);
        String[] folders = getFoldersToExclude();
        info.setFoldersToExclude(folders);
        listeners.forEach(l -> l.propertiesApplied());
    }
    
    private String[] getFileTypes() {
        return splitAndTrim(fileTypesTextArea.getText(), ",");
    }
    
    private String[] getFoldersToExclude() {
        return splitAndTrim(excludeFoldersTextArea.getText(), ",");
    }
    
    private String[] splitAndTrim(String s, String regex) {
        String[] split = s.split(regex);
        for(int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        return split;
    }
    
    private String toCommaSeparatedString(List<String> strings) {
        StringBuilder builder = new StringBuilder();
        for(String s : strings) {
            builder.append(s);
            if(strings.indexOf(s) != strings.size() - 1) builder.append(", ");
        }
        return builder.toString();
    }
}
