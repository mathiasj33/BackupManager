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
        if (fileEndings.length == 1 && fileEndings[0].equals("")) {
            filesToExclude = Collections.EMPTY_LIST;
            return;
        }
        filesToExclude = Arrays.asList(fileEndings);
    }

    public void setFoldersToExclude(String... folderNames) {
        if (folderNames.length == 1 && folderNames[0].equals("")) {
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
    public String createCommand(String target) {  //TODO: vor dem Backup noch sanity checks; UI gescheit machen (Window darf nicht geclosed werden, nicht in Backup.java)
        String command = "robocopy \"" + getPath() + "\" \"" + removeTrailingBackspace(target) + getRelativeFolderCleaned() + "\" /mir";
        if (!includeSubDirectories.get()) {
            File folder = new File(getPath());
            String[] subDirs = folder.list((File current, String name) -> new File(current, name).isDirectory());
            for (String subDir : subDirs) {
                command = appendExcludedFolder(command, subDir);
            }
        } else {
            for (String folder : foldersToExclude) {
                command = appendExcludedFolder(command, folder);
            }
        }

        for (String ending : filesToExclude) {
            command = appendExcludeFileEnding(command, ending);
        }
        return command + " /MT:8";
    }

    private String appendExcludedFolder(String command, String folder) {
        if (!command.contains("/xd"))
            command += " /xd";
        command += " \"" + getPath() + "\\" + folder + "\"";
        return command;
    }

    private String appendExcludeFileEnding(String command, String file) {
        if (!command.contains("/xf"))
            command += " /xf";
        command += " *" + file;
        return command;
    }
}
