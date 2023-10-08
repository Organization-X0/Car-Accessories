package org.Car;

public enum State {

    START(0),
    LOGIN(1),
    SIGNUP(2),
    CUSTOMER_DASHBOARD(3),
    ADMIN_DASHBOARD(4),
    INSTALLER_DASHBOARD(5),
    MANAGE_PRODUCTS(6),
    MANAGE_CATEGORIES(7),
    MANAGE_ACCOUNTS(8),
    PRODUCTS_CRUD(9),
    SEARCH_PRODUCT(10),
    ADD_PRODUCT(11),
    VIEW_ORDERS(12),
    VIEW_INSTALLATION_REQ(13);

    private final int value;
    State(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
