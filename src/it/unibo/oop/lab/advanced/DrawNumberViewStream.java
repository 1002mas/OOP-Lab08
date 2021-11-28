package it.unibo.oop.lab.advanced;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public final class DrawNumberViewStream implements DrawNumberView {
    private static final String NEW_GAME = ": a new game starts!";
    private final PrintStream out;

    public DrawNumberViewStream(final PrintStream out) {
        this.out = out;
    }

    public DrawNumberViewStream(final String fileName) throws FileNotFoundException {
        this.out = new PrintStream(new FileOutputStream(fileName));
    }

    @Override
    public void start() {
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    @Override
    public void numberIncorrect() {
        out.println("Incorrect Number.. try again");
    }

    @Override
    public void result(final DrawResult res) {
        out.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        out.println("You lost" + NEW_GAME);
    }

    @Override
    public void displayError(final String message) {
        out.println(message);
    }

}
