
package org.bitbucket.mathiasj33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;

public class FolderBackupInfo extends BackupInfo {

    private List<String> filesToExclude = new ArrayList();
    private List<String> foldersToExclude = new ArrayList();
    private BooleanProperty includeSubDirectories = new SimpleBooleanProperty(true);
    
    public FolderBackupInfo(String path) {
        super(path);
    }

    public void setFilesToExclude(String... fileEndings) {
        filesToExclude.addAll(Arrays.asList(fileEndings));
    }
    
    public void setFoldersToExclude(String... folderNames) {
        foldersToExclude.addAll(Arrays.asList(folderNames));
    }
    
    public BooleanProperty includeSubDirectoriesProperty() {
        return includeSubDirectories;
    }

    public void setIncludeSubDirectories(boolean includeSubDirectories) {
        this.includeSubDirectories.setValue(includeSubDirectories);
    }
}
