
package org.bitbucket.mathiasj33;

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
}
