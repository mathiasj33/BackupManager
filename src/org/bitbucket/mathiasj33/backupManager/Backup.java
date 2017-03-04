package org.bitbucket.mathiasj33.backupManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Backup {

    private ObservableList<BackupInfo> backupInfos;
    private StringProperty storageDirectory;

    public Backup() {
        backupInfos = FXCollections.observableArrayList();
        storageDirectory = new SimpleStringProperty("");
    }

    public void backup() {
        try {
            executeCommands(createCommands());
        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<String> createCommands() {
        List<String> commands = new ArrayList<>();
        backupInfos.forEach(info -> {
            commands.add(info.createCommand(storageDirectory.get()));
        });
        return commands;
    }

    private void executeCommands(List<String> commands) throws IOException {
        for (String cmd : commands) {
            System.out.println("Executing: " + cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLLoading.fxml"));
            FXMLLoadingController controller = new FXMLLoadingController(p.getInputStream());
            loader.setController(controller);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Loading");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    public void addBackupInfo(BackupInfo info) {
        backupInfos.add(info);
    }

    public void removeBackupInfo(BackupInfo info) {
        backupInfos.remove(info);
    }

    public ObservableList<BackupInfo> getBackupInfos() {
        return backupInfos;
    }

    public StringProperty getStorageDirectory() {
        return storageDirectory;
    }

    public void setStorageDirectory(String storageDirectory) {
        this.storageDirectory.setValue(storageDirectory);
    }
}
