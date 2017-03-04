
package org.bitbucket.mathiasj33.backupManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class BackupInfo {
    private String path;
    private StringProperty relativeFolder;

    public BackupInfo(String path) {
        this.path = path;
        relativeFolder = new SimpleStringProperty("");
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

    protected String getRelativeFolderCleaned() {
        String relativeFolderCleaned = removeTrailingBackspace(getRelativeFolder().get());
        if (!relativeFolderCleaned.equals(""))
            relativeFolderCleaned = "\\" + relativeFolderCleaned;
        return relativeFolderCleaned;
    }
    
    public StringProperty getRelativeFolder() {
        return relativeFolder;
    }

    public void setRelativeFolder(String relativeFolder) {
        this.relativeFolder.set(relativeFolder);
    }
    
    @Override
    public String toString() {
        return path;
    }
}
