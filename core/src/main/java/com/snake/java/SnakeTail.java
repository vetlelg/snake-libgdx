package com.snake.java;

import com.badlogic.gdx.graphics.Texture;

public class SnakeTail extends SnakeBodyPart {
    SnakeTail() {
        if (getDirection() == Direction.UP) setImage(new Texture("tailup.png"));
        if (getDirection() == Direction.DOWN) setImage(new Texture("taildown.png"));
        if (getDirection() == Direction.LEFT) setImage(new Texture("tailleft.png"));
        if (getDirection() == Direction.RIGHT) setImage(new Texture("tailright.png"));
        if (getDirection() == Direction.NONE) setImage(new Texture("tailup.png"));
    }
    public void update() {
        move();
        if (getDirection() == Direction.UP) setImage(new Texture("tailup.png"));
        if (getDirection() == Direction.DOWN) setImage(new Texture("taildown.png"));
        if (getDirection() == Direction.LEFT) setImage(new Texture("tailleft.png"));
        if (getDirection() == Direction.RIGHT) setImage(new Texture("tailright.png"));
    }
}
