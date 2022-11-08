package com.snake.libgdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import java.awt.Point;
import java.util.ArrayList;

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
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.getDirection().x == 0) {
            snake.setDirection(new Point(1, 0));
            snake.move();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && snake.getDirection().y == 0) {
            snake.setDirection(new Point(0, 1));
            snake.move();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snake.getDirection().y == 0) {
            snake.setDirection(new Point(0, -1));
            snake.move();
        }
    }

    public void update() {
        // check if it's time to move
        if (TimeUtils.nanoTime() - snake.getLastMoveTime() > snake.getMoveSpeed()) {
            snake.move();
        }

        // check if snakehead overlaps with apple. Respawn apple and expand snake
        if (snake.getRect().overlaps(apple.getRect())) {
            apple = new Apple();
            snake.expand();
        }

        // set texture image to snakehead depending on the direction of the snakehead
        if (snake.getDirection().x == 1) { snake.setImage(new Texture(Gdx.files.internal("headright.png"))); }
        else if (snake.getDirection().x == -1) { snake.setImage(new Texture(Gdx.files.internal("headleft.png"))); }
        else if (snake.getDirection().y == -1) { snake.setImage(new Texture(Gdx.files.internal("headdown.png"))); }
        else if (snake.getDirection().y == 1) { snake.setImage(new Texture(Gdx.files.internal("headup.png"))); }
        else { snake.setImage(new Texture(Gdx.files.internal("headup.png"))); }

        // set textue image to body
        ArrayList<SnakeBodyPart> array = snake.getArray();
        int size = array.size();
        for (int i = 0; i < size; i++) {
            SnakeBodyPart part = array.get(i);
            Rectangle rect = part.getRect();
            Rectangle previousRect = part.getPreviousRect();
            if (i+1 == size) {
                if (rect.x - previousRect.x < 0) { part.setImage(new Texture(Gdx.files.internal("tailleft.png"))); }
                else if (rect.x - previousRect.x > 0) { part.setImage(new Texture(Gdx.files.internal("tailright.png"))); }
                else if (rect.y - previousRect.y < 0) { part.setImage(new Texture(Gdx.files.internal("taildown.png"))); }
                else if (rect.y - previousRect.y > 0) { part.setImage(new Texture(Gdx.files.internal("tailup.png"))); }
                else { part.setImage(new Texture(Gdx.files.internal("taildown.png"))); }
            }
        }

    }
}
