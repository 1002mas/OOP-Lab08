package it.unibo.oop.lab.advanced;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class DrawNumberViewLog implements DrawNumberView {
    private static final String NEW_GAME = ": a new game starts!";
    private static final String DEFAULT_PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "match_log.txt";
    private final File logFile;

    public DrawNumberViewLog(final String filePath) {
        logFile = new File(filePath);
    }

    public DrawNumberViewLog() {
        this(DEFAULT_PATH);
    }

    @Override
    public void start() {
        logFile.delete();
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    @Override
    public void numberIncorrect() {
        writeOnFile("Incorrect Number.. try again");
    }

    @Override
    public void result(final DrawResult res) {
        writeOnFile(res.getDescription());
    }

    @Override
    public void limitsReached() {
        writeOnFile("You lost" + NEW_GAME);
    }

    @Override
    public void displayError(final String message) {
        writeOnFile(message);
    }

    private void writeOnFile(final String msg) {
        try (FileWriter out = new FileWriter(logFile, true); BufferedWriter writer = new BufferedWriter(out)) {
            writer.append(msg);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
