package org.Car;

public enum State {

    START(0),
    LOGIN(1),
    SIGNUP(2),
    CUSTOMER_DASHBOARD(3),
    ADMIN_DASHBOARD(4),
    INSTALLER_DASHBOARD(5);

    private final int value;
    State(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
