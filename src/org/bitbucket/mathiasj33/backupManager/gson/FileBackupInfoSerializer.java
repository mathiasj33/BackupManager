package org.bitbucket.mathiasj33.backupManager.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.bitbucket.mathiasj33.backupManager.FileBackupInfo;

public class FileBackupInfoSerializer implements JsonSerializer<FileBackupInfo> {

    @Override
    public JsonElement serialize(FileBackupInfo t, Type type, JsonSerializationContext jsc) {
        JsonObject object = new JsonObject();
        object.addProperty("type", "file");
        object.addProperty("path", t.getPath());
        return object;
    }

}
