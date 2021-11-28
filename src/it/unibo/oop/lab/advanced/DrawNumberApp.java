package it.unibo.oop.lab.advanced;

import java.util.ArrayList;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> view;

    /**
     * @param views                one or more ways to show the game 
     * @param settingsFileName
     *                             name of the file where the game setting are
     */
    public DrawNumberApp(final String settingsFileName, final DrawNumberView... views) {
        SettingsLoader sett = new SettingsLoader();
        if (!sett.getFromSettingsFromFile(settingsFileName)) {
            sett = new SettingsLoader();
        }
        System.out.println(sett.getMaximum());
        this.model = new DrawNumberImpl(sett.getMinimum(), sett.getMaximum(), sett.getAttempts());
        this.view = new ArrayList<>();
        for (final DrawNumberView v : views) {
            view.add(v);
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
        new DrawNumberApp("config.yml", new DrawNumberViewImpl(), new DrawNumberViewLog(), new DrawNumberViewStdout());
    }

}
