package com.snake.java;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private World world;

    @Override
    public void create () {
        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, World.WIDTH, World.HEIGHT);
        batch = new SpriteBatch();

        // Create the world, including the snake, the apple and other objects in the world
        world = new World();
    }

    @Override
    public void render () {
        // clear the screen with a color. The
        // arguments to clear are the red, green, blue and alpha
        // component in the range [0,1] of the color
        // to be used to clear the screen.
        ScreenUtils.clear(0.7f, 0.7f, 0.9f, 0);

        // tell the camera to update its matrices
        camera.update();

        // tell the SpriteBatch to render in the coordinate system used by the camera
        batch.setProjectionMatrix((camera.combined));

        // begin a new batch and draw the world including all its objects
        batch.begin();
        world.draw(batch);
        batch.end();

        // process user input
        world.input();

        // update the world
        world.update();

        // check if game over
        if (world.getSnake().getHead().isOutOfBounds()) {
            world = new World();
        }
    }

    @Override
    public void dispose () {
        world.dispose();
        batch.dispose();
    }
}
