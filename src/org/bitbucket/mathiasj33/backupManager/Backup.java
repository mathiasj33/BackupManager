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
    private CommandExecutor cmdExecutor;

    public Backup() {
        backupInfos = FXCollections.observableArrayList();
        storageDirectory = new SimpleStringProperty("");
        cmdExecutor = new CommandExecutor();
    }

    public void backup() {
        backup(getEmptyCallback());
    }
    
    private Callback getEmptyCallback() {
        return (a,b) -> {};
    }
    
    public void backup(Callback callback) {
        try {
            cmdExecutor.executeCommands(createCommands(), callback);
        } catch (InterruptedException | IOException ex) {
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
