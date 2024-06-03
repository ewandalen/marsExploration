package com.contaazul.marsExploration.service;

import com.contaazul.marsExploration.command.MoveCommandImpl;
import com.contaazul.marsExploration.command.Command;
import com.contaazul.marsExploration.exception.InvalidCommandException;
import com.contaazul.marsExploration.factory.CommandFactory;
import com.contaazul.marsExploration.model.Robot;
import org.springframework.stereotype.Service;

@Service
public class MarsExplorationService {

    public Robot executeCommands(String commands) {
        validateCommands(commands);

        Robot robot = new Robot();
        commands.chars().forEach(c -> {
            Command command = CommandFactory.getCommand((char) c);
            command.validateAndExecute(robot);
        });

        return robot;
    }

    private void validateCommands(String commands) {
        if (commands == null || commands.isEmpty()) {
            throw new InvalidCommandException();
        }

        if (!commands.matches("[MRL]*")) {
            throw new InvalidCommandException();
        }
    }
}
