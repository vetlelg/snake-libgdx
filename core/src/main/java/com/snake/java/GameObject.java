package com.snake.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;

public class GameObject {
    public static final int SIZE = 64;
    private float moveSpeed = 1000000000, moveLength = SIZE;
    private Texture image = new Texture(Gdx.files.internal("apple.png"));
    private Rectangle rect = new Rectangle(0, 0, SIZE, SIZE);
    private Direction direction = Direction.LEFT;
    private long lastMoveTime = TimeUtils.nanoTime();

    public void move() {
        lastMoveTime = TimeUtils.nanoTime();
        if (direction == Direction.UP) rect.y -= moveLength;
        if (direction == Direction.DOWN) rect.y += moveLength;
        if (direction == Direction.LEFT) rect.x -= moveLength;
        if (direction == Direction.RIGHT) rect.x += moveLength;
    }
    public boolean isOutOfBounds() {
        if (rect.x < 0 || rect.x > World.WIDTH - SIZE || rect.y < 0 || rect.y > World.HEIGHT - SIZE)
            return true;
        return false;
    }
    public void setRandomPosition() {
        rect.x = MathUtils.random(0, World.WIDTH-SIZE);
        rect.y = MathUtils.random(0, World.HEIGHT-SIZE);
    }

    public void dispose() { image.dispose(); }

    public void draw(SpriteBatch batch) { batch.draw(image, rect.x, rect.y); }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Point getPosition() { return new Point((int)rect.x, (int)rect.y);}
    public void setPosition(Point p) { rect.x = p.x; rect.y = p.y; }

    public Direction getDirection() { return direction; }
    public void setDirection(Direction direction) { this.direction = direction; }

    public float getMoveSpeed() { return moveSpeed; }

    public Rectangle getRect() { return rect; }

    public long getLastMoveTime() { return lastMoveTime; }
    public void setLastMoveTime(long lastMoveTime) { this.lastMoveTime = lastMoveTime; }

    public boolean overlaps(GameObject o) { return rect.overlaps(o.getRect()); }
}
