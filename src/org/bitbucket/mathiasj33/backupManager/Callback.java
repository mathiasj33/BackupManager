package org.bitbucket.mathiasj33.backupManager;

public interface Callback {
    public void progressUpdated(int commandsExecuted, int totalCommands);
}
