package org.bitbucket.mathiasj33.backupManager.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.bitbucket.mathiasj33.backupManager.FolderBackupInfo;

public class FolderBackupInfoSerializer implements JsonSerializer<FolderBackupInfo>{

    @Override
    public JsonElement serialize(FolderBackupInfo t, Type type, JsonSerializationContext jsc) {
        JsonObject object = new JsonObject();
        object.addProperty("type", "folder");
        object.addProperty("path", t.getPath());
        JsonArray filesToExclude = new JsonArray();
        t.getFilesToExclude().forEach(filesToExclude::add);
        JsonArray foldersToExclude = new JsonArray();
        t.getFoldersToExclude().forEach(foldersToExclude::add);
        object.add("filesToExclude", filesToExclude);
        object.add("foldersToExclude", foldersToExclude);
        object.addProperty("includeSubDirectories", t.includeSubDirectoriesProperty().get());
        object.addProperty("relativeFolder", t.getRelativeFolder().get());
        return object;
    }

}
