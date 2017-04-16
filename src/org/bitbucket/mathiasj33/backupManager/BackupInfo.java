
package org.bitbucket.mathiasj33.backupManager;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class BackupInfo {
    private String path;
    protected StringProperty targetSubFolder;

    public BackupInfo(String path) {
        this.path = path;
        targetSubFolder = new SimpleStringProperty("");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public abstract String createCommand(String target);
    
    protected String removeTrailingBackspace(String path) {
        if(!path.endsWith("\\")) return path;
        return path.substring(0, path.length() - 1);
    }

    protected String getTargetSubFolderCleaned() {
        String targetSubFolderCleaned = removeTrailingBackspace(getTargetSubFolder().get());
        if (!targetSubFolderCleaned.equals(""))
            targetSubFolderCleaned = "\\" + targetSubFolderCleaned;
        return targetSubFolderCleaned;
    }
    
    public StringProperty getTargetSubFolder() {
        return targetSubFolder;
    }

    public void setTargetSubFolder(String targetSubFolder) {
        this.targetSubFolder.set(targetSubFolder);
    }
    
    @Override
    public String toString() {
        return path;
    }
}
