package fr.onefox.mywarehouse.domain;

public enum TransactionType {
    WAREHOUSE_MOVEMENT_IN("WarehouseMovement-In"),
    WAREHOUSE_MOVEMENT_OUT("WarehouseMovement-Out");

    private String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
