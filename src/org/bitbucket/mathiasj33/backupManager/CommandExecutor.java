package org.bitbucket.mathiasj33.backupManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

public class CommandExecutor {

    public void executeCommands(List<String> commands, Callback callback) throws IOException, InterruptedException {
        int commandsExecuted = 0;
        int totalCommands = commands.size();
        for (String c : commands) {
            Process p = Runtime.getRuntime().exec(c);
            waitForProcessToFinish(p);

            commandsExecuted++;
            callback.progressUpdated(commandsExecuted, totalCommands);
        }
    }

    private void waitForProcessToFinish(Process p) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.ISO_8859_1));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLLoadingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
