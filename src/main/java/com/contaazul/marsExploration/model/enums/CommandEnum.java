package com.contaazul.marsExploration.model.enums;

import com.contaazul.marsExploration.exception.InvalidCommandException;

public enum CommandEnum {

    MOVE('M'), TURN_LEFT('L'), TURN_RIGHT('R');

    private final char value;

    CommandEnum(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static CommandEnum fromChar(char c) {
        for (CommandEnum charEnum : CommandEnum.values()) {
            if (charEnum.value == c) {
                return charEnum;
            }
        }
        throw new InvalidCommandException();
    }
}
