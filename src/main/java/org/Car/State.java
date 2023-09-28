package org.Car;

public enum State {
    LOGIN(0),
    MAIN(1),
    DASHBOARD(2);
    private final int value;
    private State(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
