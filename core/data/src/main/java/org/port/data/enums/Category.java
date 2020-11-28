package org.port.data.enums;

public enum Category {
    ORIGINAL(0),
    FANART(1),
    COMMISSIONED(2);
    private final int value;

    Category(int value) {
        this.value = value;
    }
}
