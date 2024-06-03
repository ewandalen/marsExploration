package com.contaazul.marsExploration.factory;

import com.contaazul.marsExploration.command.MoveCommandImpl;
import com.contaazul.marsExploration.command.Command;
import com.contaazul.marsExploration.command.TurnRightCommandImpl;
import com.contaazul.marsExploration.command.TurnLeftCommandImpl;
import com.contaazul.marsExploration.exception.InvalidCommandException;
import com.contaazul.marsExploration.model.enums.CommandEnum;

public class CommandFactory {

    public static Command getCommand(char command) {
        switch (CommandEnum.fromChar(command)) {
            case TURN_LEFT:
                return new TurnLeftCommandImpl();
            case TURN_RIGHT:
                return new TurnRightCommandImpl();
            case MOVE:
                return new MoveCommandImpl();
            default:
                throw new InvalidCommandException();
        }
    }

}
