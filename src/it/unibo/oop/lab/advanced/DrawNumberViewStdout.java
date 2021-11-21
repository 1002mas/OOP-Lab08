package it.unibo.oop.lab.advanced;

public final class DrawNumberViewStdout implements DrawNumberView {
    private static final String NEW_GAME = ": a new game starts!";

    @Override
    public void start() {
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    @Override
    public void numberIncorrect() {
        System.out.println("Incorrect Number.. try again");
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
        case YOURS_HIGH:
        case YOURS_LOW:
            System.out.println(res.getDescription());
            return;
        case YOU_WON:
            System.out.println(res.getDescription() + NEW_GAME);
            break;
        default:
            throw new IllegalStateException("Unexpected result: " + res);
        }
    }

    @Override
    public void limitsReached() {
        System.out.println("You lost" + NEW_GAME);
    }

    @Override
    public void displayError(final String message) {
        System.out.println(message);
    }

}
