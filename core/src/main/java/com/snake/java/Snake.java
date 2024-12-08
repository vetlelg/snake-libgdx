package com.snake.java;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class Snake {
    private ArrayList<SnakeBodyPart> body = new ArrayList<>();
    private SnakeHead head = new SnakeHead();
    private SnakeTail tail = new SnakeTail();

    Snake() {
        // Create the snake with initial head, tail and body positions and directions
        head.setPosition(new Point(World.WIDTH/2 - GameObject.SIZE/2, World.WIDTH/2 - GameObject.SIZE/2));
        head.setDirection(Direction.RIGHT);
        Point firstBodyPartPosition = head.getPosition();
        head.move();
        SnakeBodyPart part = new SnakeBodyPart();
        part.setPosition(firstBodyPartPosition);
        part.setDirection(Direction.RIGHT);
        Point firstTailPosition = part.getPosition();
        head.move();
        part.move();
        tail.setPosition(firstTailPosition);
        tail.setDirection(Direction.RIGHT);
    }

    public void expand() {
        setDirection(head.getDirection());
        SnakeBodyPart part = new SnakeBodyPart();
        Point lastBodyPartPosition = body.get(body.size()-1).getPosition();
        part.setPosition(lastBodyPartPosition);
        head.update();
        for (SnakeBodyPart bodyPart : body)
            bodyPart.update();

        // Set initial position to the same as the head
        part.setPosition(head.getPosition());
        body.add(part);
    }

    public boolean isOutOfBounds() { return head.isOutOfBounds(); }

    public void setDirection(Direction direction) {
        Direction prevDirection = head.getDirection();
        head.setDirection(direction);
        for (SnakeBodyPart part : body) {
            direction = part.getDirection();
            part.setDirection(prevDirection);
            prevDirection = direction;
        }
        tail.setDirection(prevDirection);
    }
    public Direction getDirection() { return head.getDirection(); }
    public SnakeHead getHead() { return head; }
    public long getLastMoveTime() { return head.getLastMoveTime(); }
    public float getMoveSpeed() { return head.getMoveSpeed(); }

    public void dispose() {
        head.dispose();
        for (SnakeBodyPart part : body)
            part.dispose();
        tail.dispose();
    }

    public void draw(SpriteBatch batch) {
        head.draw(batch);
        for (SnakeBodyPart part : body)
            part.draw(batch);
        tail.draw(batch);
    }

    public void update() {
        setDirection(head.getDirection());
        head.update();
        for (SnakeBodyPart part : body)
            part.update();
        tail.update();
    }
}
