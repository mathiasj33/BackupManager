
package org.bitbucket.mathiasj33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FolderBackupInfo extends BackupInfo {

    private List<String> filesToExclude = new ArrayList<>();
    private List<String> foldersToExclude = new ArrayList<>();
    private boolean includeSubDirectories;
    
    public FolderBackupInfo(String path) {
        super(path);
    }

    public void setFilesToExclude(String... fileEndings) {
        filesToExclude.addAll(Arrays.asList(fileEndings));
    }
    
    public void setFoldersToExclude(String... folderNames) {
        foldersToExclude.addAll(Arrays.asList(folderNames));
    }
    
    public boolean isIncludeSubDirectories() {
        return includeSubDirectories;
    }

    public void setIncludeSubDirectories(boolean includeSubDirectories) {
        this.includeSubDirectories = includeSubDirectories;
    }
}
