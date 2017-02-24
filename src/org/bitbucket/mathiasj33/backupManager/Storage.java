package org.bitbucket.mathiasj33.backupManager;

public interface Storage {
    public void store(Backup backup) throws StoreException;
    public default void tryStore(Backup backup) {
        try {
            store(backup);
        } catch(StoreException e) {e.printStackTrace(System.err);}
    }
    public Backup retrieve() throws StoreException;
}
