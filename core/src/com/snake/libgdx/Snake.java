package com.snake.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Snake extends SnakeBodyPart {
    private ArrayList<SnakeBodyPart> array = new ArrayList<SnakeBodyPart>();

    Snake() {
        setRect(new Rectangle(World.WIDTH/2 - SIZE/2, World.WIDTH/2 - SIZE/2, SIZE, SIZE));
    }

    public void expand() {
        SnakeBodyPart bodyPart = new SnakeBodyPart();
        array.add(bodyPart);
    }

    public ArrayList<SnakeBodyPart> getArray() {
        return array;
    }

    public void dispose() {
        super.dispose();
        for (SnakeBodyPart part : array) {
            part.dispose();
        }
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        for (SnakeBodyPart part : array) {
            part.draw(batch);
        }
    }

    public void move() {
        setPreviousRect(getPreviousRect());
        super.move();
        for (SnakeBodyPart part : array) {
            part.setPreviousRect(part.getPreviousRect());
            part.setRect(getPreviousRect());
            setPreviousRect(part.getPreviousRect());
        }
    }
}
