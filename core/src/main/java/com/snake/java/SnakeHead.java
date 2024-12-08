package com.snake.java;

import com.badlogic.gdx.graphics.Texture;

public class SnakeHead extends SnakeBodyPart {
    SnakeHead() {
        if (getDirection() == Direction.UP) setImage(new Texture("headup.png"));
        if (getDirection() == Direction.DOWN) setImage(new Texture("headdown.png"));
        if (getDirection() == Direction.LEFT) setImage(new Texture("headleft.png"));
        if (getDirection() == Direction.RIGHT) setImage(new Texture("headright.png"));
        if (getDirection() == Direction.NONE) setImage(new Texture("headup.png"));
    }
    public void update() {
        move();
        if (getDirection() == Direction.UP) setImage(new Texture("headup.png"));
        if (getDirection() == Direction.DOWN) setImage(new Texture("headdown.png"));
        if (getDirection() == Direction.LEFT) setImage(new Texture("headleft.png"));
        if (getDirection() == Direction.RIGHT) setImage(new Texture("headright.png"));
    }
}
