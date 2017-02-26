package org.bitbucket.mathiasj33.backupManager.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.bitbucket.mathiasj33.backupManager.BackupInfo;
import org.bitbucket.mathiasj33.backupManager.FileBackupInfo;
import org.bitbucket.mathiasj33.backupManager.FolderBackupInfo;

public class BackupInfoDeserializer implements JsonDeserializer<BackupInfo> {

    @Override
    public BackupInfo deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject obj = je.getAsJsonObject();
        if (obj.get("type").getAsString().equals("file"))
            return createFileBackupInfo(obj);
        return createFolderBackupInfo(obj);
    }

    public FileBackupInfo createFileBackupInfo(JsonObject obj) {
        FileBackupInfo info = new FileBackupInfo(obj.get("path").getAsString());
        info.setRelativeFolder(obj.get("relativeFolder").getAsString());
        return info;
    }

    public FolderBackupInfo createFolderBackupInfo(JsonObject obj) {
        String path = obj.get("path").getAsString();
        String[] filesToExclude = createStringArray(obj.get("filesToExclude").getAsJsonArray());
        String[] foldersToExclude = createStringArray(obj.get("foldersToExclude").getAsJsonArray());
        boolean includeSubDirectories = obj.get("includeSubDirectories").getAsBoolean();
        FolderBackupInfo info = new FolderBackupInfo(path);
        info.setFilesToExclude(filesToExclude);
        info.setFoldersToExclude(foldersToExclude);
        info.setIncludeSubDirectories(includeSubDirectories);
        info.setRelativeFolder(obj.get("relativeFolder").getAsString());
        return info;
    }

    public String[] createStringArray(JsonArray array) {
        List<String> strings = new ArrayList<>();
        for (JsonElement e : array) {
            strings.add(e.getAsString());
        }
        return strings.toArray(new String[strings.size()]);
    }
}
