package com.snake.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;

public class World {
    final public static int WIDTH = 1280;
    final public static int HEIGHT = 960;
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snake.getDirection().x == 0) {
            snake.setDirection(new Point(-1, 0));
            snake.move();
            //snake.getRect().x -= GameObject.SIZE;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.getDirection().x == 0) {
            snake.setDirection(new Point(1, 0));
            snake.move();
            //snake.getRect().x += GameObject.SIZE;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && snake.getDirection().y == 0) {
            snake.setDirection(new Point(0, 1));
            snake.move();
            //snake.getRect().y += GameObject.SIZE;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snake.getDirection().y == 0) {
            snake.setDirection(new Point(0, -1));
            snake.move();
            //snake.getRect().y -= GameObject.SIZE;
        }
    }

    public void update() {
        if (TimeUtils.nanoTime() - snake.getLastMoveTime() > snake.getMoveSpeed()) {
            snake.move();
        }
        if (snake.getRect().overlaps(apple.getRect())) {
            apple = new Apple();
            snake.expand();
        }
    }
}
