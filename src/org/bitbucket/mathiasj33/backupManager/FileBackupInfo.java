package org.bitbucket.mathiasj33.backupManager;

import java.io.File;

public class FileBackupInfo extends BackupInfo {

    public FileBackupInfo(String path) {
        super(path);
        this.targetSubFolder.set(new File(path).getParentFile().getName());
    }

    @Override
    public String createCommand(String target) {
        File file = new File(getPath());
        String targetCleaned = removeTrailingBackspace(target);
        String fileName = file.getName();
        return "robocopy \"" + file.getParent() + "\" \"" + targetCleaned + getTargetSubFolderCleaned() + "\" \"" + fileName + "\" /MT:8";  //robocopy "Path\To\Parent" "Path\To\Target" "fileName.ending"
    }
}
