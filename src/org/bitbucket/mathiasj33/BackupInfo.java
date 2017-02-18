
package org.bitbucket.mathiasj33;

public abstract class BackupInfo {
    private String path;

    public BackupInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    @Override
    public String toString() {
        return path;
    }
}
