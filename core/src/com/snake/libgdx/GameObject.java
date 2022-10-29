package com.snake.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;

public class GameObject {
    private float moveSpeed = 2000000, moveLength = 5;
    private Point position = new Point(), direction = new Point(), size = new Point(16, 16);
    private Texture image;
    private Rectangle rectangle = new Rectangle(position.x, position.y, size.x, size.y);
    private long lastMoveTime = TimeUtils.nanoTime();

    public void move() {
        position.x += direction.x * moveLength;
        position.y += direction.y * moveLength;
    }

    public boolean isOutOfBounds() {
        if (position.x < 0 || position.x > World.WINDOW_WIDTH - size.x || position.y < 0 || position.y > World.WINDOW_HEIGHT - size.y) {
            return true;
        }
        return false;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public float getMoveLength() {
        return moveLength;
    }

    public void setMoveLength(float moveLength) {
        this.moveLength = moveLength;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {
        this.direction = direction;
    }

    public Point getSize() {
        return size;
    }

    public void setSize(Point size) {
        this.size = size;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(long lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }
}
