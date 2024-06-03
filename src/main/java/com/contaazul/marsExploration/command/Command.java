package com.contaazul.marsExploration.command;

import com.contaazul.marsExploration.model.Robot;

public interface Command {

    void validateAndExecute(Robot robot);

    void execute(Robot robot);

}
