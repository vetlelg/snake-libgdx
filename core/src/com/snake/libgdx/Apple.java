package com.snake.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class Apple extends GameObject {
    Apple() {
        setImage(new Texture(Gdx.files.internal("apple.png")));
        setRandomPosition();
    }
}
