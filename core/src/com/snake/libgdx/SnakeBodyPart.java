package com.snake.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class SnakeBodyPart extends GameObject {
    private Rectangle previousRect = new Rectangle(0, 0, SIZE, SIZE);
    SnakeBodyPart() {
        setImage(new Texture(Gdx.files.internal("snake.png")));
    }

    public Rectangle getPreviousRect() { return previousRect; }
    public void setPreviousRectPosition(Rectangle rect) { previousRect.x = rect.x; previousRect.y = rect.y; }
}
