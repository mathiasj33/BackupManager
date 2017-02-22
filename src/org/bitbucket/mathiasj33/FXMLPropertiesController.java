
package org.bitbucket.mathiasj33;

import com.google.gson.Gson;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class FXMLPropertiesController implements Initializable {

    private FolderBackupInfo info;
    private Stage stage;
    
    @FXML
    private Label header;
    @FXML
    private TextArea fileTypesTextArea;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextArea excludeFoldersTextArea;
    
    public FXMLPropertiesController(FolderBackupInfo info, Stage stage) {
        this.info = info;
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        header.setText(header.getText() + "\n" + info.getPath());
        checkBox.selectedProperty().bindBidirectional(info.includeSubDirectoriesProperty());
        excludeFoldersTextArea.disableProperty().bind(checkBox.selectedProperty().not());
        
        fileTypesTextArea.setText(toCommaSeparatedString(info.getFilesToExclude()));
        excludeFoldersTextArea.setText(toCommaSeparatedString(info.getFoldersToExclude()));        
        
        stage.setOnCloseRequest(e -> apply());
        Platform.runLater(header::requestFocus);
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
