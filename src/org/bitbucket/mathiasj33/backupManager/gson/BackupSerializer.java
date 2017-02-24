package org.bitbucket.mathiasj33.backupManager.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.bitbucket.mathiasj33.backupManager.Backup;

public class BackupSerializer implements JsonSerializer<Backup>{

    @Override
    public JsonElement serialize(Backup t, Type type, JsonSerializationContext jsc) {
        JsonObject object = new JsonObject();
        JsonArray backupInfos = new JsonArray();
        t.getBackupInfos().forEach(info -> backupInfos.add(jsc.serialize(info)));
        object.add("backupInfos", backupInfos);
        object.addProperty("storageDirectory", t.getStorageDirectory().get());
        return object;
    }

}
