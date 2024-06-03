package com.contaazul.marsExploration.model;

import com.contaazul.marsExploration.model.enums.DirectionEnum;

public class Robot {

    private int x;

    private int y;

    private DirectionEnum directionEnum;

    public Robot() {
        this.x = 0;
        this.y = 0;
        this.directionEnum = DirectionEnum.NORTH;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirectionEnum getDirection() {
        return directionEnum;
    }

    public void setDirection(DirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ", " + directionEnum.getValue() +
                ')';
    }
}
