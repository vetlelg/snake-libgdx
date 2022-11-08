package com.snake.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class GameObject {
    public static final int SIZE = 64;
    private float moveSpeed = 1000000000, moveLength = SIZE;
    private Texture image = new Texture(Gdx.files.internal("apple.png"));
    private Rectangle rect = new Rectangle(0, 0, SIZE, SIZE);
    private Point direction = new Point();
    private long lastMoveTime = TimeUtils.nanoTime();

    public void move() {
        lastMoveTime = TimeUtils.nanoTime();
        rect.x += direction.x * moveLength;
        rect.y += direction.y * moveLength;
    }
    public boolean isOutOfBounds() {
        if (rect.x < 0 || rect.x > World.WIDTH - SIZE || rect.y < 0 || rect.y > World.HEIGHT - SIZE) {
            return true;
        }
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

    public Rectangle getRect() {
        return rect;
    }
    public void setRectPosition(Rectangle rect) { this.rect.x = rect.x; this.rect.y = rect.y; }

    public Point getDirection() { return direction; }
    public void setDirection(Point direction) { this.direction = direction; }

    public float getMoveSpeed() { return moveSpeed; }

    public long getLastMoveTime() { return lastMoveTime; }
    public void setLastMoveTime(long lastMoveTime) { this.lastMoveTime = lastMoveTime; }
}
