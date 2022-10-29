package com.snake.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class SnakeBodyPart extends GameObject {
    SnakeBodyPart(Point position, Point direction) {
        setPosition(position);
        setDirection(direction);
        setImage(new Texture(Gdx.files.internal("snake.png")));
    }
}
