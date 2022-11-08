package com.snake.libgdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Snake extends SnakeBodyPart {
    private ArrayList<SnakeBodyPart> array = new ArrayList<SnakeBodyPart>();

    Snake() {
        setRectPosition(new Rectangle(World.WIDTH/2 - SIZE/2, World.WIDTH/2 - SIZE/2, SIZE, SIZE));
    }

    public void expand() {
        SnakeBodyPart bodyPart = new SnakeBodyPart();

        // Set initial position to the same as the head
        bodyPart.getRect().x = getRect().x;
        bodyPart.getRect().y = getRect().y;
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
        setPreviousRectPosition(getRect());
        super.move();
        for (SnakeBodyPart part : array) {
            part.setPreviousRectPosition(part.getRect());
            part.setRectPosition(getPreviousRect());
            setPreviousRectPosition(part.getPreviousRect());
        }
    }
}
