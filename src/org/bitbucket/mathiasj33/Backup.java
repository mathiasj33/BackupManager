
package org.bitbucket.mathiasj33;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Backup {
    private ObservableList<BackupInfo> backupInfos;
    private StringProperty storageDirectory;
    
    public Backup() {
        backupInfos = FXCollections.observableArrayList();
        storageDirectory = new SimpleStringProperty();
    }
    
    public void addBackupInfo(BackupInfo info) {
        backupInfos.add(info);
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
