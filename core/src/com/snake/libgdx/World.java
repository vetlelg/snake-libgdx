package com.snake.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class World {
    final public static int WIDTH = 800;
    final public static int HEIGHT = 480;
    private Snake snake = new Snake();
    private Apple apple = new Apple();

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }

    public void dispose() {
        snake.dispose();
        apple.dispose();
    }

    public void draw(SpriteBatch batch) {
        apple.draw(batch);
        snake.draw(batch);
    }

    public void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snake.getDirection().x != 1) {
            snake.getDirection().x = -1;
            snake.getDirection().y = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.getDirection().x != -1) {
            snake.getDirection().x = 1;
            snake.getDirection().y = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && snake.getDirection().y != -1) {
            snake.getDirection().y = 1;
            snake.getDirection().x = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snake.getDirection().y != 1) {
            snake.getDirection().y = -1;
            snake.getDirection().x = 0;
        }
    }

    public void update() {
        snake.move();
        if (snake.getRect().overlaps(apple.getRect())) {
            apple = new Apple();
            snake.expand();
        }
    }
}
