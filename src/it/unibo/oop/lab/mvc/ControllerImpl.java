package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

public class ControllerImpl implements Controller {
    private String nextString;
    private final List<String> strHistory = new LinkedList<>();

    @Override
    public final void printNext(final String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        this.nextString = s;
    }

    @Override
    public final String getNextString() {
        return this.nextString;
    }

    @Override
    public final List<String> getStringsHistory() {
        return new LinkedList<String>(strHistory);
    }

    @Override
    public final void printCurrentString() {
        if (nextString == null) {
            throw new IllegalStateException();
        }
        System.out.println(nextString);
        strHistory.add(0, nextString);
    }

}
