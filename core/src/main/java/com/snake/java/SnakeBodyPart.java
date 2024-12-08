package com.snake.java;

import java.awt.*;


public class SnakeBodyPart extends GameObject {
    private Point prevPosition;
    private Direction prevDirection;

    public void setPrevPosition(Point prevPosition) {
        this.prevPosition = prevPosition;
    }
    public Point getPrevPosition() {
        return prevPosition;
    }
    public void setPrevDirection(Direction prevDirection) {
        this.prevDirection = prevDirection;
    }
    public Direction getPrevDirection() {
        return prevDirection;
    }

    public void update() {

        move();
    }

}
