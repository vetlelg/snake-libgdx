package com.snake.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class GameObject {
    public static final int SIZE = 16;
    private float moveSpeed = 2000000, moveLength = 5;
    private Texture image;
    private Rectangle rect = new Rectangle(0, 0, SIZE, SIZE);
    private Point direction = new Point();
    private long lastMoveTime = TimeUtils.nanoTime();

    public void move() {
        // check if it's time to move
        if (TimeUtils.nanoTime() - lastMoveTime > moveSpeed) {
            lastMoveTime = TimeUtils.nanoTime();
            rect.x += rect.x * moveLength;
            rect.y += rect.y * moveLength;
        }
    }
    public boolean isOutOfBounds() {
        if (rect.x < 0 || rect.y > World.WIDTH - SIZE || rect.y < 0 || rect.y > World.HEIGHT - rect.y) {
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
    public void setRect(Rectangle rect) { this.rect = rect; }

    public Point getDirection() { return direction; }
}
