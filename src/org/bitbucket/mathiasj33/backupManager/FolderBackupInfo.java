
package org.bitbucket.mathiasj33.backupManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FolderBackupInfo extends BackupInfo {

    private List<String> filesToExclude = new ArrayList();
    private List<String> foldersToExclude = new ArrayList();
    private BooleanProperty includeSubDirectories = new SimpleBooleanProperty(true);
    
    public FolderBackupInfo(String path) {
        super(path);
    }

    public void setFilesToExclude(String... fileEndings) {
        if(fileEndings.length == 1 && fileEndings[0].equals("")) {
            filesToExclude = Collections.EMPTY_LIST;
            return;
        }
        filesToExclude = Arrays.asList(fileEndings);
    }
    
    public void setFoldersToExclude(String... folderNames) {
        if(folderNames.length == 1 && folderNames[0].equals("")) {
            foldersToExclude = Collections.EMPTY_LIST;
            return;
        }
        foldersToExclude = Arrays.asList(folderNames);
    }

    public List<String> getFilesToExclude() {
        return filesToExclude;
    }

    public List<String> getFoldersToExclude() {
        return foldersToExclude;
    }
    
    public BooleanProperty includeSubDirectoriesProperty() {
        return includeSubDirectories;
    }

    public void setIncludeSubDirectories(boolean includeSubDirectories) {
        this.includeSubDirectories.setValue(includeSubDirectories);
    }

    @Override
    public String createCommand(String target) {
        String firstParent = "";
        File parent = new File(getPath());
        while(parent.getParent() != null) {
            parent = new File(parent.getParent());
            firstParent = parent.getAbsolutePath();
        }
        String pathWithoutBeginning = getPath().replace(firstParent, "");  //TODO: if includeSubDirectories, angeben können, wie die ordnerstruktur im backup aussehen soll, vor dem Backup noch sanity checks
        String cmd = "robocopy \"" + getPath() + "\" \"" + removeTrailingBackspace(target) + "\\" + pathWithoutBeginning + "\" /mir ";
        return cmd;
    }
}
