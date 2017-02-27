package org.bitbucket.mathiasj33.backupManager;

import java.io.File;

public class FileBackupInfo extends BackupInfo {

    public FileBackupInfo(String path) {
        super(path);
    }

    @Override
    public String createCommand(String target) {
        File file = new File(getPath());
        String targetCleaned = removeTrailingBackspace(target);
        String fileName = file.getName();
        String relativeFolderCleaned = removeTrailingBackspace(getRelativeFolder().get());
        if (!relativeFolderCleaned.equals(""))
            relativeFolderCleaned = "\\" + relativeFolderCleaned;
        return "robocopy \"" + file.getParent() + "\" \"" + targetCleaned + relativeFolderCleaned + "\" \"" + fileName + "\"";  //robocopy "Path\To\Parent" "Path\To\Target" "fileName.ending"
    }
}
