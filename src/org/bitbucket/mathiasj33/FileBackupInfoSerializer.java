package org.bitbucket.mathiasj33;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class FileBackupInfoSerializer implements JsonSerializer<FileBackupInfo> {

    @Override
    public JsonElement serialize(FileBackupInfo t, Type type, JsonSerializationContext jsc) {
        JsonObject object = new JsonObject();
        object.addProperty("type", "file");
        object.addProperty("path", t.getPath());
        return object;
    }

}
