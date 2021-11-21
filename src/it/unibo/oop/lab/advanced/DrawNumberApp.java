package it.unibo.oop.lab.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    // private static final int MIN = 0;
    // private static final int MAX = 100;
    // private static final int ATTEMPTS = 10;
    private final DrawNumber model;
    private final List<DrawNumberView> view;

    /**
     * 
     */
    public DrawNumberApp() {
        // this.model = new DrawNumberImpl(MIN, MAX, ATTEMPTS);
        SettingsLoader.loadSettings();
        this.model = new DrawNumberImpl(SettingsLoader.getMinimum(), SettingsLoader.getMaximum(), SettingsLoader.getAttempts());
        this.view = new ArrayList<>();
        this.view.add(new DrawNumberViewImpl());
        this.view.add(new DrawNumberViewLog());
        this.view.add(new DrawNumberViewStdout());
        for (final DrawNumberView v : view) {
            v.setObserver(this);
            v.start();
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView v : view) {
                v.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView v : view) {
                v.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final DrawNumberView v : view) {
                v.limitsReached();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *                 ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
