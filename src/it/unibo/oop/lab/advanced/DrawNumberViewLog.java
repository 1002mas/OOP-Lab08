package it.unibo.oop.lab.advanced;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class DrawNumberViewLog implements DrawNumberView {
    private static final String NEW_GAME = ": a new game starts!";
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator") + "match_log.txt";
    private final File logFile;

    public DrawNumberViewLog() {
        logFile = new File(PATH);
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
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            writeOnFile(res.getDescription());
            return;
        case YOU_WON:
            writeOnFile(res.getDescription() + NEW_GAME);
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
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
