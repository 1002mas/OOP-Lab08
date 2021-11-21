package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SettingsLoader {
    private static int minimum;
    private static int maximum;
    private static int attempts;
    private static boolean isLoaded;

    public static void loadSettings() {
        try (InputStream in = ClassLoader.getSystemResourceAsStream("config.yml");
                BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
            StringTokenizer row = new StringTokenizer(br.readLine());
            row.nextToken(":");
            SettingsLoader.minimum = Integer.parseInt(row.nextToken().trim());
            row = new StringTokenizer(br.readLine());
            row.nextToken(":");
            SettingsLoader.maximum = Integer.parseInt(row.nextToken().trim());
            row = new StringTokenizer(br.readLine());
            row.nextToken(":");
            SettingsLoader.attempts = Integer.parseInt(row.nextToken().trim());
            SettingsLoader.isLoaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int getMinimum() {
        checkLoaded();
        return SettingsLoader.minimum;
    }

    public static int getMaximum() {
        checkLoaded();
        return SettingsLoader.maximum;
    }

    public static int getAttempts() {
        checkLoaded();
        return SettingsLoader.attempts;
    }

    private static void checkLoaded() {
        if (!SettingsLoader.isLoaded) {
            throw new IllegalStateException("Load file settings first");
        }
    }
}
