package com.snake.java;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.*;
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && snake.getDirection() != Direction.RIGHT) {
            snake.setDirection(Direction.LEFT);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && snake.getDirection() != Direction.LEFT) {
            snake.setDirection(Direction.RIGHT);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && snake.getDirection() != Direction.DOWN) {
            snake.setDirection(Direction.UP);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && snake.getDirection() != Direction.UP) {
            snake.setDirection(Direction.DOWN);
        }
    }

    public void update() {
        // check if it's time to move
        if (TimeUtils.nanoTime() - snake.getLastMoveTime() > snake.getMoveSpeed()) {
            snake.update();
        }

        // check if snakehead overlaps with apple. Respawn apple and expand snake
        if (snake.getHead().overlaps(apple)) {
            apple = new Apple();
            snake.expand();
        }

        // set texture image to snakehead depending on the direction of the snakehead
        if (snake.getDirection().x == 1) { snake.setImage(new Texture(Gdx.files.internal("headright.png"))); }
        else if (snake.getDirection().x == -1) { snake.setImage(new Texture(Gdx.files.internal("headleft.png"))); }
        else if (snake.getDirection().y == -1) { snake.setImage(new Texture(Gdx.files.internal("headdown.png"))); }
        else if (snake.getDirection().y == 1) { snake.setImage(new Texture(Gdx.files.internal("headup.png"))); }
        else { snake.setImage(new Texture(Gdx.files.internal("headup.png"))); }

        // set texture image to body
        ArrayList<SnakeBodyPart> array = snake.getArray();
        int size = array.size();

        /*for (int i = 0; i < size; i++) {

            SnakeBodyPart part = array.get(i);
            Point position = part.getPosition();
            Point previousPosition = part.getPreviousPosition();

            if (i+1 == size) {
                if (part.getDirectionString().equals("left")) { part.setImage(new Texture(Gdx.files.internal("tailleft.png"))); }
                else if (part.getDirectionString().equals("right")) { part.setImage(new Texture(Gdx.files.internal("tailright.png"))); }
                else if (part.getDirectionString().equals("down")) { part.setImage(new Texture(Gdx.files.internal("taildown.png"))); }
                else if (part.getDirectionString().equals("up")) { part.setImage(new Texture(Gdx.files.internal("tailup.png"))); }
                else { part.setImage(new Texture(Gdx.files.internal("taildown.png"))); }
            }


            if (part.getDirectionString().equals("left")) { part.setImage(new Texture(Gdx.files.internal("tailleft.png"))); }
            else if (part.getDirectionString().equals("right")) { part.setImage(new Texture(Gdx.files.internal("tailright.png"))); }
            else if (part.getDirectionString().equals("down")) { part.setImage(new Texture(Gdx.files.internal("taildown.png"))); }
            else if (part.getDirectionString().equals("up")) { part.setImage(new Texture(Gdx.files.internal("tailup.png"))); }

        }*/

    }
}
