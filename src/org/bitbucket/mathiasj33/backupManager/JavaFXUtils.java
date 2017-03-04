package org.bitbucket.mathiasj33.backupManager;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class JavaFXUtils {

    public static File getFile(String title, Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle(title);
        return fileChooser.showOpenDialog(window);
    }

    public static File getDirectory(String title, Window window) {
        return getDirectory(title, new File(System.getProperty("user.home")), window);
    }
    
    public static File getDirectory(String title, File initialDirectory, Window window) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle(title);
        dc.setInitialDirectory(initialDirectory);
        return dc.showDialog(window);
    }
}
