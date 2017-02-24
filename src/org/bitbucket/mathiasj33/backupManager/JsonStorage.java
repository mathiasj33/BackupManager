package org.bitbucket.mathiasj33.backupManager;

import com.google.gson.GsonBuilder;
import org.bitbucket.mathiasj33.backupManager.gson.BackupDeserializer;
import org.bitbucket.mathiasj33.backupManager.gson.BackupInfoDeserializer;
import org.bitbucket.mathiasj33.backupManager.gson.BackupSerializer;
import org.bitbucket.mathiasj33.backupManager.gson.FileBackupInfoSerializer;
import org.bitbucket.mathiasj33.backupManager.gson.FolderBackupInfoSerializer;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class JsonStorage implements Storage {

    private final static String FILE_NAME = System.getProperty("user.home") + "/BackupManager/properties.json";
    private final Gson gson;
    
    public JsonStorage() {
        gson = createGson();
    }
    
    @Override
    public void store(Backup backup) throws StoreException {
        File file = new File(FILE_NAME);
        file.getParentFile().mkdirs();
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            file.createNewFile();
            pw.print(gson.toJson(backup));
            pw.flush();
        } catch (IOException ex) {
            throw new StoreException("Error saving the preferences.");
        }
    }

    @Override
    public Backup retrieve() throws StoreException {
        File file = new File(FILE_NAME);
        if(!file.exists()) throw new StoreException("The Json Storage does not exist (yet).");
        try {
            String jsonString = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            return gson.fromJson(jsonString, Backup.class);
        } catch(JsonSyntaxException | IOException e) {
            throw new StoreException("Error loading the preferences");
        }
    }

    private Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FolderBackupInfo.class, new FolderBackupInfoSerializer());
        gsonBuilder.registerTypeAdapter(FileBackupInfo.class, new FileBackupInfoSerializer());
        gsonBuilder.registerTypeAdapter(Backup.class, new BackupSerializer());
        gsonBuilder.registerTypeAdapter(BackupInfo.class, new BackupInfoDeserializer());
        gsonBuilder.registerTypeAdapter(Backup.class, new BackupDeserializer());
        return gsonBuilder.create();
    }
}
