package com.snake.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Apple extends GameObject {
    Apple() {
        setImage(new Texture(Gdx.files.internal("apple1.png")));
        setRandomPosition();
    }
}
