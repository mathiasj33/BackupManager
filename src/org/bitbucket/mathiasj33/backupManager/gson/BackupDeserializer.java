package org.bitbucket.mathiasj33.backupManager.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.bitbucket.mathiasj33.backupManager.Backup;
import org.bitbucket.mathiasj33.backupManager.BackupInfo;

public class BackupDeserializer implements JsonDeserializer<Backup> {

    @Override
    public Backup deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Backup backup = new Backup();
        JsonObject object = je.getAsJsonObject();
        JsonArray infos = object.get("backupInfos").getAsJsonArray();
        infos.forEach(e -> backup.addBackupInfo(jdc.deserialize(e, BackupInfo.class)));

        backup.setStorageDirectory(object.get("storageDirectory").getAsString());
        return backup;
    }

}
