package com.contaazul.marsExploration.command;

import com.contaazul.marsExploration.model.enums.DirectionEnum;
import com.contaazul.marsExploration.model.Robot;

public class TurnLeftCommandImpl implements Command {

    @Override
    public void validateAndExecute(Robot robot) {
        execute(robot);
    }

    @Override
    public void execute(Robot robot) {
        switch (robot.getDirection()) {
            case NORTH:
                robot.setDirection(DirectionEnum.WEST);
                break;
            case SOUTH:
                robot.setDirection(DirectionEnum.EAST);
                break;
            case EAST:
                robot.setDirection(DirectionEnum.NORTH);
                break;
            case WEST:
                robot.setDirection(DirectionEnum.SOUTH);
                break;
        }
    }
}
