package org.Car;

public enum State {
    LOGIN(0),
    SIGNUP(1),
    MAIN(2),
    ADMIN_DASHBOARD(3),
    INSTALLER_DASHBOARD(4),
    START(5);
    private final int value;
    private State(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
