package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class SettingsLoader {

    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;

    private int minimum = MIN;
    private int maximum = MAX;
    private int attempts = ATTEMPTS;

    public int getMinimum() {
        return this.minimum;
    }

    public int getMaximum() {
        return this.maximum;
    }

    public int getAttempts() {
        return this.attempts;
    }

    public boolean getFromSettingsFromFile(final String fileName) {
        try (InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
            StringTokenizer row = new StringTokenizer(br.readLine());
            row.nextToken(":");
            this.minimum = Integer.parseInt(row.nextToken().trim());
            row = new StringTokenizer(br.readLine());
            row.nextToken(":");
            this.maximum = Integer.parseInt(row.nextToken().trim());
            row = new StringTokenizer(br.readLine());
            row.nextToken(":");
            this.attempts = Integer.parseInt(row.nextToken().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attempts > 0 && minimum < maximum;
    }
    /*
     * reset to default settings
     * */

}
