package com.contaazul.marsExploration.command;

import com.contaazul.marsExploration.exception.InvalidCommandException;
import com.contaazul.marsExploration.model.Robot;

public class MoveCommandImpl implements Command {

    private final int MAX_X = 4;
    private final int MAX_Y = 4;

    @Override
    public void validateAndExecute(Robot robot) {
        switch (robot.getDirection()) {
            case EAST -> {
                if (robot.getX() + 1 > MAX_X) {
                    throw new InvalidCommandException();
                }
            }
            case WEST -> {
                if (robot.getX() - 1 < 0) {
                    throw new InvalidCommandException();
                }
            }
            case NORTH -> {
                if (robot.getY() + 1 > MAX_Y) {
                    throw new InvalidCommandException();
                }
            }
            case SOUTH -> {
                if (robot.getY() - 1 < 0) {
                    throw new InvalidCommandException();
                }
            }
        }

        execute(robot);

    }

    @Override
    public void execute(Robot robot) {
        switch (robot.getDirection()) {
            case NORTH:
                robot.setY(robot.getY() + 1);
                break;
            case SOUTH:
                robot.setY(robot.getY() - 1);
                break;
            case EAST:
                robot.setX(robot.getX() + 1);
                break;
            case WEST:
                robot.setX(robot.getX() - 1);
                break;
        }
    }
}
