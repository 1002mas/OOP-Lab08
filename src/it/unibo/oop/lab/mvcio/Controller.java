package it.unibo.oop.lab.mvcio;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import it.unibo.oop.lab.iogui.BadIOGUI;

/**
 * 
 */
public class Controller {
    private static final String DEFAULT_FILE = System.getProperty("user.home") + System.getProperty("file.separator")
            + "output.txt";

    private File f;

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    /*
     * @JavaDoc
     */

    public Controller() {
        this.f = new File(DEFAULT_FILE);
    }

    public final void setCurrentFile(final String path) {
        if (path != null) {
            try {
                f = new File(path);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public final File getCurrentFile() {
        return new File(f.getAbsolutePath());
    }

    public final String getFilePath() {
        return f.getAbsolutePath();
    }

    public final void writeString(final String s) throws IOException {
        if (f != null) {
            final OutputStream writeStream = new FileOutputStream(f);
            final DataOutputStream writer = new DataOutputStream(writeStream);
            try (writeStream; writer) {
                writer.writeChars(s);
            } finally {
                writeStream.close();
                writer.close();
            }
        }
    }
}
