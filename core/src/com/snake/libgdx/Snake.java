package com.snake.libgdx;

import java.awt.*;
import java.util.ArrayList;

public class Snake extends SnakeBodyPart {
    private ArrayList<SnakeBodyPart> snake = new ArrayList<SnakeBodyPart>();

    Snake() {
        super(new Point(), new Point());
        setPosition(new Point(World.WINDOW_WIDTH/2 - getSize().x/2, World.WINDOW_WIDTH/2 - getSize().y/2));
    }

    private void expandSnake() {
        SnakeBodyPart bodyPart = new SnakeBodyPart(getPosition(), new Point());
        snake.add(bodyPart);
    }
}
