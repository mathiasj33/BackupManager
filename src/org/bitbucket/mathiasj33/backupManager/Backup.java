
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
        for(String cmd : commands) {
            System.out.println("Executing: " + cmd);
            //Runtime.getRuntime().exec(cmd);
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
