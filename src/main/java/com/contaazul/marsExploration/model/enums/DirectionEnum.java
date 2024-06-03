package com.contaazul.marsExploration.model.enums;

public enum DirectionEnum {
    NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');

    private final char value;

    DirectionEnum(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }
}
