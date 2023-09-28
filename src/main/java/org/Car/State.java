package org.Car;

public enum State {
    LOGIN(0),
    SIGNUP(1),
    MAIN(2),

    DASHBOARD(3);
    private final int value;
    private State(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
